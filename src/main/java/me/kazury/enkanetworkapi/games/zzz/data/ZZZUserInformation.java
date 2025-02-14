package me.kazury.enkanetworkapi.games.zzz.data;

import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.games.starrail.data.SRUserInformation;
import me.kazury.enkanetworkapi.games.starrail.data.conversion.SRUnconvertedUser;
import me.kazury.enkanetworkapi.games.zzz.data.conversion.ZZZUnconvertedUser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * The basic data class for a Zenless Zone Zero user.
 * <br>This class contains all the information that is available on a user's profile.
 */
public class ZZZUserInformation {
    private final String nickname;
    private final int level;
    private final String signature;

    private List<ZZZUserCharacter> detailCharacters;

    public ZZZUserInformation(@NotNull String nickname,
                              final int level,
                              @NotNull String signature,
                              @NotNull List<ZZZUserCharacter> detailCharacters) {
        this.nickname = nickname;
        this.level = level;
        this.signature = signature;
        this.detailCharacters = detailCharacters;
    }

    /**
     * The nickname of this user that is publicly displayed.
     */
    @NotNull
    public String getNickname() {
        return this.nickname;
    }

    /**
     * The level of this user.
     * <br>Ranges from 1 to 60.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The signature of this user which is publicly displayed.
     */
    @NotNull
    public String getSignature() {
        return this.signature;
    }

    /**
     * Performs an action after this object has been initialized.
     * @param info The action to perform.
     */
    protected void doActionAfter(@NotNull Consumer<ZZZUserInformation> info) {
        info.accept(this);
    }

    /**
     * Sets the detail characters of this user.
     * @param detailCharacters The detail characters to set.
     */
    public void setDetailCharacters(@NotNull List<ZZZUserCharacter> detailCharacters) {
        this.detailCharacters = detailCharacters;
    }

    /**
     * The characters that this user has in their showcase.
     */
    @NotNull
    public List<ZZZUserCharacter> getDetailCharacters() {
        return this.detailCharacters;
    }

    /**
     * Converts this object to a {@link SRUserInformation} object.
     * <br>
     * <br>You should always prefer using this class over {@link SRUnconvertedUser} (detailed in this class)
     * <br>You may also want to cache this object, but you shouldn't care unless you really need the data now and not some point later
     * (as this might be a heavy operation, depending on how often you call this method)
     *
     * @param enkaUser The old {@link SRUnconvertedUser} object which was received using {@link EnkaNetworkAPI#fetchHonkaiUser(long, Consumer)}.
     * @return The converted {@link SRUserInformation} object.
     */
    @NotNull
    public static ZZZUserInformation fromEnkaUser(@NotNull ZZZUnconvertedUser enkaUser) {
        final ZZZUnconvertedUser.PlayerInfo info = enkaUser.getPlayerInfo();
        final ZZZUnconvertedUser.SocialDetail socialDetail = info.getSocialDetail();
        final ZZZUnconvertedUser.ProfileDetail profileDetail = socialDetail.getProfileDetail();

        final List<ZZZUnconvertedUser.ShowcaseAvatar> avatarList = info.getAvatarList();

        final ZZZUserInformation information = new ZZZUserInformation(
                profileDetail.getNickname(),
                profileDetail.getLevel(),
                socialDetail.getDescription(),
                Collections.emptyList()
        );

        information.doActionAfter(data -> {
            if (avatarList.isEmpty()) {
                data.setDetailCharacters(new ArrayList<>());
                return;
            }

            final List<ZZZUserCharacter> characters = avatarList.stream()
                    .map(avatar -> {
                        // No need to worry about null stuff yet, these are booleans and primitives
                        final ZZZUserCharacter character = new ZZZUserCharacter(
                                avatar.getId(),
                                avatar.getObtainmentTimestamp(),
                                avatar.getLevel(),
                                avatar.getWeaponEffectState(),
                                avatar.isHidden(),
                                avatar.getClaimedRewards(),
                                avatar.getPromotionLevel(),
                                avatar.getCoreSkillEnhancement(),
                                avatar.getSkinId(),
                                avatar.getTalentLevel(),
                                avatar.getExp()
                        );

                        final ZZZUnconvertedUser.ShowcaseAvatarWeapon weapon = avatar.getWeapon();
                        if (weapon != null) {
                            character.setWeapon(new ZZZCharacterWeapon(
                                    weapon.isAvailable(),
                                    weapon.isLocked(),
                                    weapon.getId(),
                                    weapon.getUid(),
                                    weapon.getLevel(),
                                    weapon.getBreakLevel(),
                                    weapon.getExp(),
                                    weapon.getUpgradeLevel()
                            ));
                        }
                        return character;
                    })
                    .toList();
            data.setDetailCharacters(characters);
        });
        return information;
    }
}
