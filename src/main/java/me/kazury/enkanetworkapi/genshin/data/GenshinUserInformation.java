package me.kazury.enkanetworkapi.genshin.data;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.enka.EnkaParser;
import me.kazury.enkanetworkapi.genshin.data.conversion.GenshinUnconvertedUser;

import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

/**
 * The basic data class for a Genshin Impact user.
 * <br>This class contains all the information that is available on a user's profile.
 */
public class GenshinUserInformation {
    private final String nickName;
    private final String signature;
    private final int level;
    private final int worldLevel;
    private final int namecardId;
    private final int achievementCompleted;
    private final int towerFloorIndex;
    private final int towerLevelIndex;
    private final long uid;
    private final Pair<Long, Long> profilePictureId;
    private List<GenshinShowcaseCharacter> showcaseCharacters;
    private List<GenshinNamecard> namecards;
    private List<GenshinUserCharacter> characters;

    public GenshinUserInformation(@NotNull String nickName,
                                  @NotNull String signature,
                                  final int level,
                                  final int worldLevel,
                                  final int namecardId,
                                  final int achievementCompleted,
                                  final int towerFloorIndex,
                                  final int towerLevelIndex,
                                  @NotNull List<GenshinShowcaseCharacter> showcaseCharacters,
                                  @NotNull List<GenshinNamecard> namecards,
                                  @NotNull Pair<Long, Long> profilePictureId,
                                  @NotNull List<GenshinUserCharacter> characters,
                                  final long uid) {
        this.nickName = nickName;
        this.signature = signature;
        this.level = level;
        this.worldLevel = worldLevel;
        this.namecardId = namecardId;
        this.achievementCompleted = achievementCompleted;
        this.towerFloorIndex = towerFloorIndex;
        this.towerLevelIndex = towerLevelIndex;
        this.showcaseCharacters = showcaseCharacters;
        this.namecards = namecards;
        this.profilePictureId = profilePictureId;
        this.characters = characters;
        this.uid = uid;
    }

    /**
     * The nickname of the player.
     */
    @NotNull
    public String getNickname() {
        return this.nickName;
    }

    /**
     * The signature of the player.
     */
    @NotNull
    public String getSignature() {
        return this.signature;
    }

    /**
     * The adventure rank of this player
     * <br>Ranges from 1 to 60.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The world level of the player.
     * This is a range from 0 to 8.
     */
    public int getWorldLevel() {
        return this.worldLevel;
    }

    /**
     * The namecard ID that the player currently has on their profile for everyone to see.
     *
     * @see EnkaNetworkAPI#getGenshinNamecard(int)
     */
    public int getNamecardId() {
        return this.namecardId;
    }

    /**
     * The amount of achievements completed, range from 0 to max achievements.
     */
    public int getAchievementsCompleted() {
        return this.achievementCompleted;
    }

    /**
     * The highest abyss floor this user has reached (current rotation).
     */
    public int getAbyssFloor() {
        return this.towerFloorIndex;
    }

    /**
     * The highest chamber this user has reached in the Spiral Abyss (current rotation).
     */
    public int getAbyssChamber() {
        return this.towerLevelIndex;
    }

    /**
     * The list of characters that the player has in their showcase.
     */
    @NotNull
    public List<GenshinShowcaseCharacter> getShowcaseCharacters() {
        return this.showcaseCharacters;
    }

    /**
     * The list of namecards that the player has in their showcase.
     */
    @NotNull
    public List<GenshinNamecard> getNamecards() {
        return this.namecards;
    }

    /**
     * The uid of this object.
     */
    public long getUid() {
        return this.uid;
    }

    /**
     * The ID of the character's profile picture (This will always exist).
     * <br>As of version 4.1, it is not safe anymore to use this ID to get the profile picture by character data.
     *
     * @see EnkaNetworkAPI#getGenshinProfileIdentifier(Pair)
     */
    @NotNull
    public Pair<Long, Long> getProfilePictureId() {
        return this.profilePictureId;
    }

    /**
     * This is similar to what {@link #showcaseCharacters} is.
     * <br>This is the character data of all characters located in {@link #showcaseCharacters}.
     * <br>The showcase characters only gives info about who is in the showcase, not their data.
     */
    @NotNull
    public List<GenshinUserCharacter> getCharacters() {
        return this.characters;
    }

    /**
     * Performs an action after this object has been initialized.
     * @param info The action to perform.
     */
    protected void doActionAfter(@NotNull Consumer<GenshinUserInformation> info) {
        info.accept(this);
    }

    /**
     * Sets the showcase characters of this user.
     * @param showcaseCharacters The showcase characters to set.
     */
    public void setShowcaseCharacters(@NotNull List<GenshinShowcaseCharacter> showcaseCharacters) {
        this.showcaseCharacters = showcaseCharacters;
    }

    /**
     * Sets the namecards of this user.
     * @param namecards The namecards to set.
     */
    public void setNamecards(@NotNull List<GenshinNamecard> namecards) {
        this.namecards = namecards;
    }

    /**
     * Sets the characters of this user.
     * @param characters The characters to set.
     */
    public void setCharacters(@NotNull List<GenshinUserCharacter> characters) {
        this.characters = characters;
    }

    /**
     * Converts this object to a {@link GenshinUserInformation} object.
     * <br>
     * <br>You should always prefer using this class over {@link GenshinUnconvertedUser} (detailed in this class)
     * <br>You may also want to cache this object, but you shouldn't care unless you really need the data now and not some point later
     * (as this might be a heavy operation, depending on how often you call this method)
     *
     * @param enkaUser The old {@link GenshinUnconvertedUser} object which was received using {@link EnkaNetworkAPI#fetchGenshinUser(long, Consumer)}.
     * @return The converted {@link GenshinUserInformation} object.
     */
    @NotNull
    @SuppressWarnings("unchecked")
    public static GenshinUserInformation fromEnkaUser(@NotNull GenshinUnconvertedUser enkaUser) {
        // You're always free to do a PR and clean this mess up. :)

        final GenshinUnconvertedUser.PlayerInfo playerInfoData = enkaUser.getPlayerInfo();
        final GenshinUnconvertedUser.ProfilePicture profileData = playerInfoData.getProfilePicture();
        final String signature = playerInfoData.getSignature();
        final Pair<Long, Long> profileId = filterId(profileData);

        final GenshinUserInformation user = new GenshinUserInformation(
                playerInfoData.getNickname(),
                signature == null ? "" : signature,
                playerInfoData.getLevel(),
                playerInfoData.getWorldLevel(),
                playerInfoData.getNameCardId(),
                playerInfoData.getFinishAchievementNum(),
                playerInfoData.getTowerFloorIndex(),
                playerInfoData.getTowerLevelIndex(),
                Collections.emptyList(),
                Collections.emptyList(),
                profileId,
                Collections.emptyList(),
                Long.parseLong(enkaUser.getUid())
        );

        user.doActionAfter(data -> {
            final GenshinUnconvertedUser.PlayerInfo playerInfo = enkaUser.getPlayerInfo();
            if (playerInfo.getShowAvatarInfoList() != null) {
                data.setShowcaseCharacters(playerInfo.getShowAvatarInfoList().stream().map(showAvatarInfo -> {
                    final int avatarId = showAvatarInfo.getAvatarId();
                    final int level = showAvatarInfo.getLevel();
                    final int costumeId = showAvatarInfo.getCostumeId();
                    return new GenshinShowcaseCharacter(avatarId, level, costumeId);
                }).toList());
            }

            if (playerInfo.getShowNameCardIdList() != null) {
                data.setNamecards(playerInfo.getShowNameCardIdList().stream().map(id -> {
                    final String name = EnkaCaches.getNamecardName(id);
                    final String namecardURL = EnkaNetworkAPI.BASE_GENSHIN_UI_URL + name + ".png";
                    return new GenshinNamecard(id, namecardURL);
                }).toList());
            }

            if (enkaUser.getAvatarInfoList() == null) {
                data.setCharacters(Collections.emptyList());
                return;
            }

            data.setCharacters(enkaUser.getAvatarInfoList().stream().map(avatarInfo -> {
                final int id = avatarInfo.getAvatarId();
                final List<Integer> talentIdList = avatarInfo.getTalentIdList();
                final int constellation = talentIdList == null ? 0 : talentIdList.size();

                final Map<GenshinFightProp, Double> fightProperties = new HashMap<>();
                final List<Integer> talentLevels = new ArrayList<>();

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

                // Talent levels of this character
                for (Map.Entry<String, Integer> entry : avatarInfo.getSkillLevelMap().entrySet()) {
                    talentLevels.add(entry.getValue());
                }

                // Applying weapon variable and adding artifacts to list
                for (GenshinUnconvertedUser.EquipData equipData : avatarInfo.getEquipList()) {
                    final GenshinUnconvertedUser.ArtifactData artifactData = equipData.getReliquary();
                    final GenshinUnconvertedUser.FlatData flatData = equipData.getFlat();
                    if (artifactData == null) {
                        // Since artifact data is null, we know that this is a weapon
                        final GenshinUnconvertedUser.WeaponData weaponData = equipData.getWeapon();
                        final List<GenshinUserWeapon.WeaponStat> weaponStats = new ArrayList<>();

                        for (GenshinUnconvertedUser.SubData subData : flatData.getWeaponStats()) {
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
                                .star(flatData.getRankLevel())
                                .stats(weaponStats)
                                .build();
                        continue;
                    }
                    final GenshinUnconvertedUser.ArtifactMainData mainStat = flatData.getReliquaryMainstat();
                    final GenshinAppendProp appendProp = GenshinAppendProp.fromKey(mainStat.getMainPropId());
                    final List<GenshinArtifact.ArtifactStat> subStats = new ArrayList<>();
                    if (appendProp == null) continue;

                    // Retrieve substats of artifact
                    for (GenshinUnconvertedUser.SubData substat : flatData.getReliquarySubstats()) {
                        final GenshinAppendProp subProp = GenshinAppendProp.fromKey(substat.getAppendPropId());
                        if (subProp == null) continue;
                        final double rawValue = substat.getStatValue();

                        subStats.add(new GenshinArtifact.ArtifactStat(
                                subProp.getId(),
                                subProp.getAcceptor().accept(rawValue),
                                rawValue,
                                subProp.getValueType()
                        ));
                    }
                    final GenshinArtifactType type = EnkaParser.parseArtifact(flatData.getEquipType());
                    if (type == null) throw new UpdateLibraryException();
                    final double rawValue = mainStat.getStatValue();

                    artifacts.add(GenshinArtifact.builder()
                            .level(artifactData.getLevel() - 1)
                            .type(type)
                            .appendPropIds(artifactData.getAppendPropIdList())
                            .mainStats(new GenshinArtifact.ArtifactStat(
                                    appendProp.getId(),
                                    appendProp.getAcceptor().accept(rawValue),
                                    rawValue,
                                    appendProp.getValueType()
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
                        .talentLevels(new GenshinCharacterTalents(talentLevels.get(0), talentLevels.get(1), talentLevels.get(2)))
                        .build();
            }).toList());
        });

        return user;
    }

    /**
     * HoYo decided to go on a format which represents a map, but ALWAYS only has one entry
     *
     * @param map The map to resolve
     * @return The first value of the map, or -1 if the map is null or empty
     */
    private static int resolveFirst(@Nullable Map<?, Integer> map) {
        if (map == null) return 1;
        for (int value : map.values()) {
            return value + 1;
        }
        return -1;
    }

    /**
     * Filters the id of a profile picture,
     * <br>As of version 4.1, HoYo decided to change how profiles work.
     *
     * @param profile The profile to filter
     * @return Pair of id and 0 or pair of avatar id and costume id
     */
    @NotNull
    private static Pair<Long, Long> filterId(@NotNull GenshinUnconvertedUser.ProfilePicture profile) {
        // either id OR avatar id AND costume id
        final long id = profile.getId();
        if (id != 0) return new Pair<>(id, 0L);
        final long avatarId = profile.getAvatarId();
        final long costumeId = profile.getCostumeId();
        return new Pair<>(avatarId, costumeId);
    }
}
