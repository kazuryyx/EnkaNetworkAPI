package me.kazury.enkanetworkapi.starrail.data.conversion;

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
public class SRUnconvertedUser implements IExpiryTime {
    private DetailInfo detailInfo;
    /**
     * stfu intellij this cannot be final because of gson
     */
    private int ttl = 60;
    private String uid;

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

        public int getHeadIcon() {
            return this.headIcon;
        }

        public List<AvatarDetail> getAvatarDetailList() {
            return this.avatarDetailList;
        }

        public String getNickname() {
            return this.nickname;
        }

        public int getLevel() {
            return this.level;
        }

        public String getSignature() {
            return this.signature;
        }

        public long getUid() {
            return this.uid;
        }

        public int getFriendCount() {
            return this.friendCount;
        }

        public RecordInfo getRecordInfo() {
            return this.recordInfo;
        }

        public int getWorldLevel() {
            return this.worldLevel;
        }

        public boolean isDisplayAvatar() {
            return this.isDisplayAvatar;
        }

        public String getPlatform() {
            return this.platform;
        }
    }

    public static class AvatarDetail {
        private int avatarId;
        private int rank;
        private int level;
        private int promotion; // Ascension, null if level < 20
        private EquipmentInfo equipment;
        private List<SkillTreeData> skillTreeList;
        private List<RelicData> relicList;

        public int getAvatarId() {
            return this.avatarId;
        }

        public int getRank() {
            return this.rank;
        }

        public int getLevel() {
            return this.level;
        }

        public int getPromotion() {
            return this.promotion;
        }

        public EquipmentInfo getEquipment() {
            return this.equipment;
        }

        public List<SkillTreeData> getSkillTreeList() {
            return this.skillTreeList;
        }

        public List<RelicData> getRelicList() {
            return this.relicList;
        }
    }

    public static class RecordInfo {
        private ChallengeInfo challengeInfo;
        private int equipmentCount;
        private int maxRogueChallengeScore;
        private int achievementCount;
        private int avatarCount;

        private int bookCount;
        private int relicCount;
        private int musicCount;

        public int getBookCount() {
            return this.bookCount;
        }

        public int getRelicCount() {
            return this.relicCount;
        }

        public int getMusicCount() {
            return this.musicCount;
        }

        public ChallengeInfo getChallengeInfo() {
            return this.challengeInfo;
        }

        public int getEquipmentCount() {
            return this.equipmentCount;
        }

        public int getMaxRogueChallengeScore() {
            return this.maxRogueChallengeScore;
        }

        public int getAchievementCount() {
            return this.achievementCount;
        }

        public int getAvatarCount() {
            return this.avatarCount;
        }
    }

    public static class ChallengeInfo {
        private int scheduleMaxLevel;
        private int scheduleGroupId;
        private int noneScheduleMaxLevel;
        private int abyssLevel;
        private int abyssStarCount;

        public int getAbyssLevel() {
            return this.abyssLevel;
        }

        public int getAbyssStarCount() {
            return this.abyssStarCount;
        }

        public int getScheduleMaxLevel() {
            return this.scheduleMaxLevel;
        }

        public int getScheduleGroupId() {
            return this.scheduleGroupId;
        }

        public int getNoneScheduleMaxLevel() {
            return this.noneScheduleMaxLevel;
        }
    }

    public static class EquipmentInfo {
        private int tid;
        private int rank;
        private int level;
        private int promotion;
        private EquipmentFlatData _flat;

        public int getTid() {
            return this.tid;
        }

        public int getRank() {
            return this.rank;
        }

        public int getLevel() {
            return this.level;
        }

        public int getPromotion() {
            return this.promotion;
        }

        public EquipmentFlatData get_flat() {
            return this._flat;
        }
    }

    public static class EquipmentFlatData {
        private List<EquipmentFlatProp> props;
        private long name;

        public List<EquipmentFlatProp> getProps() {
            return this.props;
        }

        public long getName() {
            return this.name;
        }
    }

    public static class SkillTreeData {
        private int pointId;
        private int level;

        public int getPointId() {
            return this.pointId;
        }

        public int getLevel() {
            return this.level;
        }
    }

    public static class EquipmentFlatProp {
        private String type;
        private double value;

        public String getType() {
            return this.type;
        }

        public double getValue() {
            return this.value;
        }
    }

    public static class RelicData {
        private int level;
        private int mainAffixId;
        private int type;
        private int tid;
        private RelicFlatData _flat;

        public int getLevel() {
            return this.level;
        }

        public int getMainAffixId() {
            return this.mainAffixId;
        }

        public int getType() {
            return this.type;
        }

        public int getTid() {
            return this.tid;
        }

        public RelicFlatData get_flat() {
            return this._flat;
        }
    }

    public static class RelicFlatData {
        private List<RelicFlatProp> props;
        private long setName;
        private long setID;

        public List<RelicFlatProp> getProps() {
            return this.props;
        }

        public long getSetName() {
            return this.setName;
        }

        public long getSetID() {
            return this.setID;
        }
    }

    public static class RelicFlatProp {
        private String type;
        private double value;

        public String getType() {
            return this.type;
        }

        public double getValue() {
            return this.value;
        }
    }

    public DetailInfo getDetailInfo() {
        return this.detailInfo;
    }

    @Override
    public int getTtl() {
        return this.ttl;
    }

    public String getUid() {
        return this.uid;
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
