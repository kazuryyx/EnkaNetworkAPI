package me.kazury.enkanetworkapi.genshin.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.enka.EnkaParser;
import me.kazury.enkanetworkapi.genshin.data.conversion.EnkaUserInformation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

/**
 * The basic data class for a Genshin Impact user.
 * <br>This class contains all the information that is available on a user's profile.
 */
@Builder
@Getter
@Setter
public class GenshinUserInformation {
    /**
     * The nickname of the player.
     */
    private final String nickName;
    /**
     * The signature of the player.
     */
    private final String signature;
    /**
     * The world level of the player.
     * This is a range from 0 to 8.
     */
    private final int worldLevel;
    /**
     * The namecard ID that the player currently has on their profile for everyone to see.
     * @see EnkaNetworkAPI#getNamecard(int) 
     */
    private final int namecardId;
    /**
     * The amount of achievements completed, range from 0 to max achievements.
     */
    private final int achievementCompleted;
    /**
     * The highest abyss floor this user has reached.
     */
    private final int towerFloorIndex;
    /**
     * The highest chamber this user has reached in the Spiral Abyss.
     */
    private final int towerLevelIndex;
    /**
     * The list of characters that the player has in their showcase.
     */
    private List<GenshinShowcaseCharacter> showcaseCharacters = new ArrayList<>();
    /**
     * This is similar to what showcaseCharacters is, players can put namecards up for show
     * <br>Though nobody really cares about this feature.
     */
    private List<GenshinNamecard> namecards = new ArrayList<>();
    /**
     * The ID of the character's profile picture (This will always exist).
     * @see EnkaNetworkAPI#getCharacterData(long) 
     */
    private final long profilePictureId;

    /**
     * This is similar to what {@link #showcaseCharacters} is.
     * <br>This is the character data of all characters located in {@link #showcaseCharacters}.
     * <br>The showcase characters only gives info about who is in the showcase, not their data.
     */
    private List<GenshinUserCharacter> characters = new ArrayList<>();

    protected void doActionAfter(@NotNull Consumer<GenshinUserInformation> info) {
        info.accept(this);
    }

    /**
     * Converts this object to a {@link GenshinUserInformation} object.
     * <br>
     * <br>You should always prefer using this class over {@link EnkaUserInformation} (detailed in this class)
     * <br>You may also want to cache this object, but you shouldn't care unless you really need the data now and not some point later
     * (as this might be a heavy operation, depending on how often you call this method)
     *
     * <br><br><b>Example</b>:
     * <pre>{@code
     * final EnkaNetworkAPI api = new EnkaNetworkAPI();
     *
     * api.fetchUser(722777337, (user) -> {
     *      final GenshinUserInformation genshinUser = fromEnkaUser(user);
     *      // do action here
     * });
     * }</pre>
     *
     * @param enkaUser The old {@link EnkaUserInformation} object which was received using {@link EnkaNetworkAPI#fetchUser(long, Consumer)}.
     * @return The converted {@link GenshinUserInformation} object.
     */
    @NotNull
    @SuppressWarnings("unchecked")
    public static GenshinUserInformation fromEnkaUser(@NotNull EnkaUserInformation enkaUser) {
        // You're always free to do a PR and clean this mess up. :)

        final GenshinUserInformation user = GenshinUserInformation.builder()
                .nickName(enkaUser.getPlayerInfo().getNickname())
                .signature(enkaUser.getPlayerInfo().getSignature())
                .worldLevel(enkaUser.getPlayerInfo().getWorldLevel())
                .namecardId(enkaUser.getPlayerInfo().getNameCardId())
                .achievementCompleted(enkaUser.getPlayerInfo().getFinishAchievementNum())
                .towerFloorIndex(enkaUser.getPlayerInfo().getTowerFloorIndex())
                .towerLevelIndex(enkaUser.getPlayerInfo().getTowerLevelIndex())
                .profilePictureId(enkaUser.getPlayerInfo().getProfilePicture().getAvatarId())
                .build();

        user.doActionAfter(data -> {
            final EnkaUserInformation.PlayerInfo playerInfo = enkaUser.getPlayerInfo();
            if (playerInfo.getShowAvatarInfoList() != null) {
                data.setShowcaseCharacters(playerInfo.getShowAvatarInfoList().stream().map(showAvatarInfo -> {
                    final int avatarId = showAvatarInfo.getAvatarId();
                    final int level = showAvatarInfo.getLevel();
                    final int costumeId = showAvatarInfo.getCostumeId();
                    return new GenshinShowcaseCharacter(avatarId, level, costumeId);
                }).toList());
            }  else {
                data.setShowcaseCharacters(Collections.emptyList());
            }

            if (playerInfo.getShowNameCardIdList() != null) {
                data.setNamecards(playerInfo.getShowNameCardIdList().stream().map(id -> {
                    final String name = EnkaCaches.getNamecardName(id);
                    final String namecardURL = EnkaNetworkAPI.BASE_UI_URL + name + ".png";
                    return new GenshinNamecard(id, namecardURL);
                }).toList());
            } else {
                data.setNamecards(Collections.emptyList());
            }

            if (enkaUser.getAvatarInfoList() != null) {
                data.setCharacters(enkaUser.getAvatarInfoList().stream().map(avatarInfo -> {
                    final int id = avatarInfo.getAvatarId();
                    final int constellation = avatarInfo.getTalentIdList() == null ? 0 : avatarInfo.getTalentIdList().size();

                    final Map<GenshinFightProp, Double> fightProperties = new HashMap<>();
                    final List<GenshinArtifact> artifacts = new ArrayList<>();
                    GenshinUserWeapon weapon = null;

                    // Properties of this character
                    for (Map.Entry<String, Double> entry : avatarInfo.getFightPropMap().entrySet()) {
                        final String key = entry.getKey();
                        final double value = entry.getValue();

                        final GenshinFightProp fightProp = GenshinFightProp.fromKey(key);
                        if (fightProp == null) continue;
                        fightProperties.put(fightProp, value);
                    }

                    // Applying weapon variable and adding artifacts to list
                    for (EnkaUserInformation.EquipData equipData : avatarInfo.getEquipList()) {
                        final EnkaUserInformation.ArtifactData artifactData = equipData.getReliquary();
                        final EnkaUserInformation.FlatData flatData = equipData.getFlat();
                        if (artifactData == null) {
                            final EnkaUserInformation.WeaponData weaponData = equipData.getWeapon();
                            final List<GenshinUserWeapon.WeaponStat> weaponStats = new ArrayList<>();

                            for (EnkaUserInformation.SubData subData : flatData.getWeaponStats()) {
                                final GenshinAppendProp parsedProp = GenshinAppendProp.fromKey(subData.getAppendPropId());
                                if (parsedProp == null) continue;
                                final double rawValue = subData.getStatValue();

                                weaponStats.add(new GenshinUserWeapon.WeaponStat(
                                        parsedProp.getId(),
                                        parsedProp.getAcceptor().accept(rawValue),
                                        rawValue
                                ));
                            }

                            weapon = GenshinUserWeapon.builder()
                                    .weaponLevel(weaponData.getLevel())
                                    .weaponAscension(weaponData.getPromoteLevel())
                                    .weaponRefinement(resolveFirst(weaponData.getAffixMap()))
                                    .nameTextMapHash(flatData.getNameTextMapHash())
                                    .weaponIcon(flatData.getIcon())
                                    .stats(weaponStats)
                                    .build();
                            continue;
                        }
                        final EnkaUserInformation.ArtifactMainData mainStat = flatData.getReliquaryMainstat();
                        final GenshinAppendProp appendProp = GenshinAppendProp.fromKey(mainStat.getMainPropId());
                        final List<GenshinArtifact.ArtifactStat> subStats = new ArrayList<>();
                        if (appendProp == null) continue;

                        for (EnkaUserInformation.SubData substat : flatData.getReliquarySubstats()) {
                            final GenshinAppendProp subProp = GenshinAppendProp.fromKey(substat.getAppendPropId());
                            if (subProp == null) continue;
                            final double rawValue = substat.getStatValue();

                            subStats.add(new GenshinArtifact.ArtifactStat(
                                    subProp.getId(), subProp.getAcceptor().accept(rawValue), rawValue
                            ));
                        }
                        final GenshinArtifactType type = EnkaParser.parseArtifact(flatData.getEquipType());
                        if (type == null) {
                            System.out.println("Unhandled artifact type: " + flatData.getEquipType());
                            continue;
                        }
                        final double rawValue = mainStat.getStatValue();

                        artifacts.add(GenshinArtifact.builder()
                                .level(artifactData.getLevel() - 1)
                                .type(type)
                                .mainStats(new GenshinArtifact.ArtifactStat(
                                        appendProp.getId(), appendProp.getAcceptor().accept(rawValue), rawValue
                                ))
                                .subStats(subStats)
                                .icon(flatData.getIcon())
                                .setNameTextMapHash(flatData.getSetNameTextMapHash())
                                .build());
                    }
                    final Map<String, Object> levelMap = ((Map<String, Object>) avatarInfo.getPropMap().get("4001"));
                    final Map<String, Object> ascensionMap = ((Map<String, Object>) avatarInfo.getPropMap().get("1002"));
                    final Map<String, Object> experienceMap = ((Map<String, Object>) avatarInfo.getPropMap().get("1001"));

                    return GenshinUserCharacter.builder()
                            .id(id)
                            .constellation(constellation)
                            .fightProperties(fightProperties)
                            .artifacts(artifacts)
                            .currentLevel(Integer.parseInt((String) levelMap.getOrDefault("val", "1")))
                            .currentAscension(Integer.parseInt((String) ascensionMap.getOrDefault("val", "1")))
                            .currentExperience(Integer.parseInt((String) experienceMap.getOrDefault("val", "-1")))
                            .friendshipLevel(avatarInfo.getFetterInfo().getExpLevel())
                            .equippedWeapon(weapon)
                            .build();
                }).toList());
            } else {
                data.setCharacters(Collections.emptyList());
            }
        });

        return user;
    }

    private static int resolveFirst(@Nullable Map<?, Integer> map) {
        if (map == null) {
            return 1;
        }
        for (int value : map.values()) {
            return value + 1;
        }
        return -1;
    }
}
