package me.kazury.enkanetworkapi.starrail.data;

import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.starrail.data.conversion.SRUnconvertedUser;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * The basic data class for a Honkai: Star Rail user.
 * <br>This class contains all the information that is available on a user's profile.
 */
public class SRUserInformation {
    private final String nickname;
    private final int level;
    private final String signature;
    private final long uid;
    private final int fwiends;
    private final int equilibriumLevel;
    private final int simulatedUniverse;

    private final int bookCount;
    private final int relicCount;
    private final int abyssLevel;
    private final int abyssStarCount;
    private final int musicCount;

    private final SRPlatform platform;
    private List<SRUserCharacter> detailCharacters;

    public SRUserInformation(@NotNull String nickname,
                             final int level,
                             @NotNull String signature,
                             final long uid,
                             final int fwiends,
                             final int equilibriumLevel,
                             final int simulatedUniverse,
                             @NotNull SRPlatform platform,
                             @NotNull List<SRUserCharacter> detailCharacters,
                             final int bookCount,
                             final int relicCount,
                             final int musicCount,
                             final int abyssLevel,
                             final int abyssStarCount) {
        this.nickname = nickname;
        this.level = level;
        this.signature = signature;
        this.uid = uid;
        this.fwiends = fwiends;
        this.equilibriumLevel = equilibriumLevel;
        this.simulatedUniverse = simulatedUniverse;
        this.platform = platform;
        this.detailCharacters = detailCharacters;

        this.abyssLevel = abyssLevel;
        this.abyssStarCount = abyssStarCount;

        this.bookCount = bookCount;
        this.relicCount = relicCount;
        this.musicCount = musicCount;
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
     * <br>Ranges from 1 to 70.
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
     * The uid of this user. This is so your friends can add you.
     */
    public long getUid() {
        return this.uid;
    }

    /**
     * The amount of fwiends that this user has.
     * <br>Yes, it's fwiends, not friends.
     */
    public int getFwiends() {
        return this.fwiends;
    }

    /**
     * The equilibrium level of this user.
     * <br>In Genshin terms: World Level.
     */
    public int getEquilibriumLevel() {
        return this.equilibriumLevel;
    }

    /**
     * The maximum "planet"? that this user has unlocked in the simulated universe
     */
    public int getSimulatedUniverse() {
        return this.simulatedUniverse;
    }

    /**
     * The platform that this player is playing on.
     * <br><i>or created their account?</i>
     */
    @NotNull
    public SRPlatform getPlatform() {
        return this.platform;
    }

    /**
     * The characters that this user has in their showcase.
     */
    @NotNull
    public List<SRUserCharacter> getDetailCharacters() {
        return this.detailCharacters;
    }

    /**
     * The amount of music this user has unlocked.
     * <br>Note: This will be 0 if the user did not log in after the version 2.2 update
     */
    public int getMusicCount() {
        return this.musicCount;
    }

    /**
     * The amount of relics the user currently has in their inventory
     * <br>Note: This will be 0 if the user did not log in after the version 2.2 update
     */
    public int getRelicCount() {
        return this.relicCount;
    }

    /**
     * The amount of stages this user beat in the "Pure Fiction" mode
     * <br>Note: This does not have any limitation such as "need to log in after 2.2"
     */
    public int getPureFictionLevel() {
        return this.abyssLevel;
    }

    /**
     * The amount of stars this user has achieved in the "Pure Fiction" mode
     * <br>Note: This will be 0 if the user did not log in after the version 2.2 update
     */
    public int getPureFictionStars() {
        return this.abyssStarCount;
    }

    /**
     * The amount of books the user has unlocked.
     * <br>Note: This will be 0 if the user did not log in after the version 2.2 update
     */
    public int getBookCount() {
        return this.bookCount;
    }

    /**
     * Performs an action after this object has been initialized.
     * @param info The action to perform.
     */
    protected void doActionAfter(@NotNull Consumer<SRUserInformation> info) {
        info.accept(this);
    }

    /**
     * Sets the detail characters of this user.
     * @param detailCharacters The detail characters to set.
     */
    public void setDetailCharacters(List<SRUserCharacter> detailCharacters) {
        this.detailCharacters = detailCharacters;
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
    public static SRUserInformation fromEnkaUser(@NotNull SRUnconvertedUser enkaUser) {
        // You're always free to do a PR and clean this mess up. :)
        final SRUnconvertedUser.DetailInfo detailInfoData = enkaUser.getDetailInfo();
        final SRUnconvertedUser.RecordInfo recordInfo = detailInfoData.getRecordInfo();
        final SRUnconvertedUser.ChallengeInfo challengeInfo = recordInfo == null ? null: recordInfo.getChallengeInfo();

        final String platform = detailInfoData.getPlatform();

        final SRUserInformation user = new SRUserInformation(
                detailInfoData.getNickname(),
                detailInfoData.getLevel(),
                detailInfoData.getSignature(),
                detailInfoData.getUid(),
                detailInfoData.getFriendCount(),
                detailInfoData.getWorldLevel(),
                recordInfo == null ? 0: recordInfo.getMaxRogueChallengeScore(),
                platform == null ? SRPlatform.UNKNOWN: SRPlatform.valueOf(platform.toUpperCase()),
                Collections.emptyList(),
                recordInfo == null ? 0: recordInfo.getBookCount(),
                recordInfo == null ? 0: recordInfo.getRelicCount(),
                recordInfo == null ? 0: recordInfo.getMusicCount(),
                challengeInfo == null ? 0: challengeInfo.getAbyssLevel(),
                challengeInfo == null ? 0: challengeInfo.getAbyssStarCount()
        );

        user.doActionAfter(data -> {
            if (!detailInfoData.isDisplayAvatar()) {
                data.setDetailCharacters(new ArrayList<>());
                return;
            }

            data.setDetailCharacters(enkaUser.getDetailInfo().getAvatarDetailList().stream().map(avatar -> {
                SRLightcone lightcone = null;

                final int eidolon = avatar.getRank();
                final int ascension = avatar.getPromotion();
                final int level = avatar.getLevel();
                final int avatarId = avatar.getAvatarId();
                final List<SRRelic> relics = new ArrayList<>();

                // Adding weapon to variable
                final SRUnconvertedUser.EquipmentInfo equipment = avatar.getEquipment();
                if (equipment != null) {
                    final List<SRLightcone.LightconeStat> stats = new ArrayList<>();

                    // Getting weapon sub stat information
                    final SRUnconvertedUser.EquipmentFlatData flat = equipment.get_flat();
                    for (SRUnconvertedUser.EquipmentFlatProp subData : flat.getProps()) {
                        final SRAppendProp parsedProp = SRAppendProp.fromKey(subData.getType());
                        if (parsedProp == null) continue;
                        final double rawValue = subData.getValue();

                        stats.add(new SRLightcone.LightconeStat(
                                parsedProp.getKey(),
                                parsedProp.getAcceptor().accept(rawValue),
                                rawValue
                        ));
                    }

                    lightcone = new SRLightcone(
                            equipment.getRank(),
                            equipment.getLevel(),
                            equipment.getPromotion(),
                            equipment.getTid(),
                            stats,
                            flat.getName()
                    );
                }

                // Getting relic information
                final List<SRUnconvertedUser.RelicData> relicDataList = Objects.requireNonNullElse(avatar.getRelicList(), new ArrayList<>());
                for (SRUnconvertedUser.RelicData relicData : relicDataList) {
                    final int relicLevel = relicData.getLevel();
                    final SRUnconvertedUser.RelicFlatData flat = relicData.get_flat();
                    final List<SRRelic.RelicStat> subStats = new ArrayList<>();

                    // Retrieve relic sub stats, first one is always the main stat
                    final List<SRUnconvertedUser.RelicFlatProp> props = flat.getProps();

                    for (int i = 0; i < props.size(); i++) {
                        if (i == 0) continue; // The main stat is not a sub stat
                        final SRUnconvertedUser.RelicFlatProp subData = props.get(i);
                        final SRAppendProp parsedProp = SRAppendProp.fromKey(subData.getType());
                        if (parsedProp == null) continue;
                        double value = subData.getValue();

                        if (parsedProp.getValueType() == SRAppendProp.ValueType.PERCENTAGE) {
                            value *= 100;
                        }

                        subStats.add(new SRRelic.RelicStat(
                                parsedProp.getKey(),
                                parsedProp.getAcceptor().accept(value),
                                value
                        ));
                    }

                    final SRUnconvertedUser.RelicFlatProp mainStat = props.get(0);
                    final SRAppendProp prop = SRAppendProp.fromKey(mainStat.getType());
                    if (prop == null) continue;
                    double mainStatValue = mainStat.getValue();

                    if (prop.getValueType() == SRAppendProp.ValueType.PERCENTAGE) {
                        mainStatValue *= 100;
                    }

                    final SRRelic.RelicStat main = new SRRelic.RelicStat(
                            prop.getKey(),
                            prop.getAcceptor().accept(mainStatValue),
                            mainStatValue
                    );

                    relics.add(new SRRelic(
                            relicLevel,
                            SRRelicType.fromId(relicData.getType()),
                            main,
                            subStats,
                            flat.getSetName(),
                            flat.getSetID()
                    ));
                }

                // Tree elements
                final List<SRSkillTreeElement> elements = new ArrayList<>();

                for (SRUnconvertedUser.SkillTreeData treeData : avatar.getSkillTreeList()) {
                    elements.add(new SRSkillTreeElement(
                            treeData.getPointId(),
                            treeData.getLevel()
                    ));
                }

                return SRUserCharacter.builder()
                        .eidolon(eidolon)
                        .ascension(ascension)
                        .relics(relics)
                        .level(level)
                        .avatarId(avatarId)
                        .lightcone(lightcone)
                        .treeElements(elements)
                        .build();
            }).toList());
        });

        return user;
    }
}
