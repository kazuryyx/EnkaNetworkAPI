package me.kazury.enkanetworkapi.starrail.data.conversion;

import lombok.Getter;
import me.kazury.enkanetworkapi.starrail.data.SRUserInformation;
import me.kazury.enkanetworkapi.util.IExpiryTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This class is used for converting json to this class.
 * <br>You should never prefer using this class and instead use {@link SRUserInformation}
 * <br>This class contains a lot of nullability, and will cause issues if you try to use it.
 * <br>If you are wondering what to use in alternative, take a look at {@link SRUserInformation#fromEnkaUser(SRUnconvertedUser)}.
 * @see SRUserInformation
 */
@Getter
public class SRUnconvertedUser implements IExpiryTime {
    private DetailInfo detailInfo;
    private int ttl;
    private String uid;

    @Getter
    public static class DetailInfo {
        private int headIcon;
        private List<AvatarDetail> avatarDetailList;
        private String nickname;
        private int level;
        private String signature;
        private long uid;
        private int friendCount;
        private RecordInfo recordInfo;
        private int worldLevel;
        private boolean isDisplayAvatar;
        private String platform;
    }

    @Getter
    public static class AvatarDetail {
        private int avatarId;
        private int rank;
        private int level;
        private int promotion; // Ascension, null if level < 20
        private EquipmentInfo equipment;
        private List<SkillTreeData> skillTreeList;
        private List<RelicData> relicList;
    }

    @Getter
    public static class RecordInfo {
        private ChallengeInfo challengeInfo;
        private int equipmentCount;
        private int maxRogueChallengeScore;
        private int achievementCount;
        private int avatarCount;
    }

    @Getter
    public static class ChallengeInfo {
        private int scheduleMaxLevel;
        private int scheduleGroupId; // optional
        private int noneScheduleMaxLevel; // optional
    }

    @Getter
    public static class EquipmentInfo {
        private int tid;
        private int rank;
        private int level;
        private int promotion; // Light cone ascended, in genshin terms "refinement"
        private EquipmentFlatData _flat;
    }

    @Getter
    public static class EquipmentFlatData {
        private List<EquipmentFlatProp> props;
        private long name;
    }

    @Getter
    public static class SkillTreeData {
        private int pointId;
        private int level;
    }

    @Getter
    public static class EquipmentFlatProp {
        private String type;
        private double value;
    }

    @Getter
    public static class RelicData {
        private int level;
        private int mainAffixId;
        private int type;
        private int tid;
        private RelicFlatData _flat;
    }

    @Getter
    public static class RelicFlatData {
        private List<RelicFlatProp> props;
        private long setName;
        private long setID;
    }

    @Getter
    public static class RelicFlatProp {
        private String type;
        private double value;
    }

    /**
     * Converts this object to {@link SRUserInformation}
     * <br>Please read the documentation of this class to understand why you <i>might</i> want this.
     * @return A {@link SRUserInformation} object.
     */
    @NotNull
    public SRUserInformation toStarRailUser() {
        return SRUserInformation.fromEnkaUser(this);
    }
}
