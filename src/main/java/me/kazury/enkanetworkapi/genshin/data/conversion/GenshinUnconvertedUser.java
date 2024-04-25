package me.kazury.enkanetworkapi.genshin.data.conversion;

import com.google.gson.annotations.SerializedName;
import me.kazury.enkanetworkapi.genshin.data.GenshinUserInformation;
import me.kazury.enkanetworkapi.util.IExpiryTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * This class is used for converting json to this class.
 * <br>You should never prefer using this class and instead use {@link GenshinUserInformation}
 * <br>This class contains a lot of nullability, and will cause issues if you try to use it.
 * <br>If you are wondering what to use in alternative, take a look at {@link GenshinUserInformation#fromEnkaUser(GenshinUnconvertedUser)}.
 * @see GenshinUserInformation
 */
public class GenshinUnconvertedUser implements IExpiryTime {
    private PlayerInfo playerInfo;
    private List<AvatarInfo> avatarInfoList;
    private int ttl;
    private String uid;

    public static class PlayerInfo {
        private String nickname;
        private int level;
        private String signature;
        private int worldLevel;
        private int nameCardId;
        private int finishAchievementNum;
        private int towerFloorIndex;
        private int towerLevelIndex;
        private List<ShowAvatarInfo> showAvatarInfoList;
        private List<Integer> showNameCardIdList;
        private ProfilePicture profilePicture;

        public String getNickname() {
            return this.nickname;
        }

        public int getLevel() {
            return this.level;
        }

        public String getSignature() {
            return this.signature;
        }

        public int getWorldLevel() {
            return this.worldLevel;
        }

        public int getNameCardId() {
            return this.nameCardId;
        }

        public int getFinishAchievementNum() {
            return this.finishAchievementNum;
        }

        public int getTowerFloorIndex() {
            return this.towerFloorIndex;
        }

        public int getTowerLevelIndex() {
            return this.towerLevelIndex;
        }

        public List<ShowAvatarInfo> getShowAvatarInfoList() {
            return this.showAvatarInfoList;
        }

        public List<Integer> getShowNameCardIdList() {
            return this.showNameCardIdList;
        }

        public ProfilePicture getProfilePicture() {
            return this.profilePicture;
        }
    }

    public static class AvatarInfo {
        private int avatarId;
        private Map<String, Object> propMap;
        private List<Integer> talentIdList;
        private Map<String, Double> fightPropMap;
        private int skillDepotId;
        private List<Integer> inherentProudSkillList;
        private Map<String, Integer> skillLevelMap;

        private List<EquipData> equipList;
        private FetterInfo fetterInfo;

        public int getAvatarId() {
            return this.avatarId;
        }

        public Map<String, Object> getPropMap() {
            return this.propMap;
        }

        public List<Integer> getTalentIdList() {
            return this.talentIdList;
        }

        public Map<String, Double> getFightPropMap() {
            return this.fightPropMap;
        }

        public int getSkillDepotId() {
            return this.skillDepotId;
        }

        public List<Integer> getInherentProudSkillList() {
            return this.inherentProudSkillList;
        }

        public Map<String, Integer> getSkillLevelMap() {
            return this.skillLevelMap;
        }

        public List<EquipData> getEquipList() {
            return this.equipList;
        }

        public FetterInfo getFetterInfo() {
            return this.fetterInfo;
        }
    }

    public static class ShowAvatarInfo {
        private int avatarId;
        private int level;
        private int costumeId;

        public int getAvatarId() {
            return this.avatarId;
        }

        public int getLevel() {
            return this.level;
        }

        public int getCostumeId() {
            return this.costumeId;
        }
    }

    public static class FetterInfo {
        private int expLevel;

        public int getExpLevel() {
            return this.expLevel;
        }
    }

    public static class EquipData {
        private long itemId;
        private ArtifactData reliquary;
        private WeaponData weapon;

        private FlatData flat;

        public long getItemId() {
            return this.itemId;
        }

        public ArtifactData getReliquary() {
            return this.reliquary;
        }

        public WeaponData getWeapon() {
            return this.weapon;
        }

        public FlatData getFlat() {
            return this.flat;
        }
    }

    public static class ArtifactData {
        private int level;
        private int mainPropId;
        private List<Integer> appendPropIdList;

        public int getLevel() {
            return this.level;
        }

        public int getMainPropId() {
            return this.mainPropId;
        }

        public List<Integer> getAppendPropIdList() {
            return this.appendPropIdList;
        }
    }

    public static class FlatData {
        private String nameTextMapHash;
        private String setNameTextMapHash;
        private int rankLevel;
        private List<SubData> weaponStats;
        private ArtifactMainData reliquaryMainstat;
        private List<SubData> reliquarySubstats;
        private String icon;
        private String equipType;

        public String getNameTextMapHash() {
            return this.nameTextMapHash;
        }

        public String getSetNameTextMapHash() {
            return this.setNameTextMapHash;
        }

        public int getRankLevel() {
            return this.rankLevel;
        }

        public List<SubData> getWeaponStats() {
            return this.weaponStats;
        }

        public ArtifactMainData getReliquaryMainstat() {
            return this.reliquaryMainstat;
        }

        public List<SubData> getReliquarySubstats() {
            return this.reliquarySubstats;
        }

        public String getIcon() {
            return this.icon;
        }

        public String getEquipType() {
            return this.equipType;
        }
    }

    public static class WeaponData {
        private int level;
        private int promoteLevel;
        private Map<String, Integer> affixMap;

        public int getLevel() {
            return this.level;
        }

        public int getPromoteLevel() {
            return this.promoteLevel;
        }

        public Map<String, Integer> getAffixMap() {
            return this.affixMap;
        }
    }

    public static class ArtifactMainData {
        private String mainPropId;
        private double statValue;

        public String getMainPropId() {
            return this.mainPropId;
        }

        public double getStatValue() {
            return this.statValue;
        }
    }

    public static class SubData {
        private String appendPropId;
        private double statValue;

        public String getAppendPropId() {
            return this.appendPropId;
        }

        public double getStatValue() {
            return this.statValue;
        }
    }

    public static class ProfilePicture {
        private long avatarId;
        private long id;

        public long getAvatarId() {
            return this.avatarId;
        }

        public long getId() {
            return this.id;
        }
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }

    public List<AvatarInfo> getAvatarInfoList() {
        return this.avatarInfoList;
    }

    @Override
    public int getTtl() {
        return this.ttl;
    }

    @NotNull
    public String getUid() {
        return this.uid;
    }

    /**
     * Converts this object to {@link GenshinUserInformation}
     * <br>Please read the documentation of this class to understand why you <i>might</i> want this.
     * @return A {@link GenshinUserInformation} object.
     */
    @NotNull
    public GenshinUserInformation toGenshinUser() {
        return GenshinUserInformation.fromEnkaUser(this);
    }
}
