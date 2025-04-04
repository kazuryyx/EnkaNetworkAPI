package me.kazury.enkanetworkapi.games.zzz.data.conversion;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.kazury.enkanetworkapi.games.zzz.data.ZZZUserInformation;
import me.kazury.enkanetworkapi.util.IExpiryTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This class is used for converting json to this class.
 * <br>You should never prefer using this class and instead use {@link ZZZUserInformation}
 * <br>This class contains a lot of nullability, and will cause issues if you try to use it.
 * <br>If you are wondering what to use in alternative, take a look at {@link ZZZUserInformation#fromEnkaUser(ZZZUnconvertedUser)}.
 * @see ZZZUserInformation
 */
public class ZZZUnconvertedUser implements IExpiryTime {
    @JsonProperty("PlayerInfo")
    private PlayerInfo playerInfo;

    private String uid;
    private int ttl;

    public static class PlayerInfo {
        @JsonProperty("SocialDetail")
        private SocialDetail socialDetail;

        @JsonProperty("ShowcaseDetail")
        private ShowcaseDetail showcaseDetail;

        public SocialDetail getSocialDetail() {
            return this.socialDetail;
        }

        public ShowcaseDetail getShowcaseDetail() {
            return showcaseDetail;
        }
    }

    public static class ShowcaseDetail {
        @JsonProperty("AvatarList")
        private List<ShowcaseAvatar> avatarList;

        public List<ShowcaseAvatar> getAvatarList() {
            return this.avatarList;
        }
    }

    public static class SocialDetail {
        @JsonProperty("MedalList")
        private List<Medal> medalList;

        @JsonProperty("ProfileDetail")
        private ProfileDetail profileDetail;

        @JsonProperty("Desc")
        private String description;

        public List<Medal> getMedalList() {
            return this.medalList;
        }

        public ProfileDetail getProfileDetail() {
            return this.profileDetail;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static class ProfileDetail {
        @JsonProperty("Nickname")
        private String nickname;

        @JsonProperty("AvatarId")
        private int avatarId;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("Title")
        private int title;

        @JsonProperty("ProfileId")
        private int profileId;

        @JsonProperty("CallingCardId")
        private int callingCardId;

        public String getNickname() {
            return this.nickname;
        }

        public int getAvatarId() {
            return this.avatarId;
        }

        public int getLevel() {
            return this.level;
        }

        public int getTitle() {
            return this.title;
        }

        public int getProfileId() {
            return this.profileId;
        }

        public int getCallingCardId() {
            return this.callingCardId;
        }
    }

    public static class Medal {
        @JsonProperty("Value")
        private int value;

        @JsonProperty("MedalType")
        private int medalType;

        @JsonProperty("MedalIcon")
        private int medalIcon;

        public int getValue() {
            return this.value;
        }

        public int getMedalType() {
            return this.medalType;
        }

        public int getMedalIcon() {
            return this.medalIcon;
        }
    }

    public static class ShowcaseAvatar {
        @JsonProperty("WeaponEffectState")
        private int weaponEffectState;

        @JsonProperty("EquippedList")
        private List<EquippedDriveDisk> equippedDriveDisks;

        @JsonProperty("SkillLevelList")
        private List<SkillLevel> skillLevels;

        @JsonProperty("TalentToggleList")
        private List<Boolean> talentToggleList;

        @JsonProperty("ClaimedRewardList")
        private List<Integer> claimedRewards;

        @JsonProperty("IsHidden")
        private boolean hidden;

        @JsonProperty("Id")
        private int id;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("PromotionLevel")
        private int promotionLevel;

        @JsonProperty("Exp")
        private int exp;

        @JsonProperty("SkinId")
        private int skinId;

        @JsonProperty("TalentLevel")
        private int talentLevel;

        @JsonProperty("CoreSkillEnhancement")
        private int coreSkillEnhancement;

        @JsonProperty("ObtainmentTimestamp")
        private int obtainmentTimestamp;

        @JsonProperty("Weapon")
        private ShowcaseAvatarWeapon weapon;

        public int getWeaponEffectState() {
            return this.weaponEffectState;
        }

        public List<EquippedDriveDisk> getEquippedDriveDisks() {
            return this.equippedDriveDisks;
        }

        public List<SkillLevel> getSkillLevels() {
            return this.skillLevels;
        }

        public List<Boolean> getTalentToggleList() {
            return this.talentToggleList;
        }

        public List<Integer> getClaimedRewards() {
            return this.claimedRewards;
        }

        public boolean isHidden() {
            return this.hidden;
        }

        public int getId() {
            return this.id;
        }

        public int getLevel() {
            return this.level;
        }

        public int getPromotionLevel() {
            return this.promotionLevel;
        }

        public int getExp() {
            return this.exp;
        }

        public int getSkinId() {
            return this.skinId;
        }

        public int getTalentLevel() {
            return this.talentLevel;
        }

        public int getCoreSkillEnhancement() {
            return this.coreSkillEnhancement;
        }

        public int getObtainmentTimestamp() {
            return this.obtainmentTimestamp;
        }

        public ShowcaseAvatarWeapon getWeapon() {
            return this.weapon;
        }
    }

    public static class ShowcaseAvatarWeapon {
        @JsonProperty("IsAvailable")
        private boolean available;

        @JsonProperty("IsLocked")
        private boolean locked;

        @JsonProperty("Id")
        private int id;

        @JsonProperty("Uid")
        private int uid;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("BreakLevel")
        private int breakLevel;

        @JsonProperty("Exp")
        private int exp;

        @JsonProperty("UpgradeLevel")
        private int upgradeLevel;

        public boolean isAvailable() {
            return available;
        }

        public boolean isLocked() {
            return this.locked;
        }

        public int getId() {
            return this.id;
        }

        public int getUid() {
            return this.uid;
        }

        public int getLevel() {
            return this.level;
        }

        public int getBreakLevel() {
            return this.breakLevel;
        }

        public int getExp() {
            return this.exp;
        }

        public int getUpgradeLevel() {
            return this.upgradeLevel;
        }
    }

    public static class SkillLevel {
        @JsonProperty("Level")
        private int level;

        @JsonProperty("Index")
        private int index;

        public int getLevel() {
            return this.level;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public static class EquippedDriveDisk {
        @JsonProperty("Slot")
        private int slot;

        @JsonProperty("Equipment")
        private DriveDiskEquipment equipmentInfo;

        public int getSlot() {
            return this.slot;
        }

        public DriveDiskEquipment getEquipmentInfo() {
            return this.equipmentInfo;
        }
    }

    public static class DriveDiskEquipment {
        @JsonProperty("RandomPropertyList")
        private List<Property> randomProperties;

        @JsonProperty("MainPropertyList")
        private List<Property> mainProperties;

        @JsonProperty("IsAvailable")
        private boolean available;

        @JsonProperty("IsLocked")
        private boolean locked;

        @JsonProperty("IsTrash")
        private boolean trash;

        @JsonProperty("Id")
        private int id;

        @JsonProperty("Uid")
        private int uid;

        @JsonProperty("Level")
        private int level;

        @JsonProperty("BreakLevel")
        private int breakLevel;

        @JsonProperty("Exp")
        private int experience;

        public List<Property> getRandomProperties() {
            return this.randomProperties;
        }

        public List<Property> getMainProperties() {
            return this.mainProperties;
        }

        public boolean isAvailable() {
            return this.available;
        }

        public boolean isLocked() {
            return this.locked;
        }

        public boolean isTrash() {
            return this.trash;
        }

        public int getId() {
            return this.id;
        }

        public int getUid() {
            return this.uid;
        }

        public int getLevel() {
            return this.level;
        }

        public int getBreakLevel() {
            return this.breakLevel;
        }

        public int getExperience() {
            return this.experience;
        }
    }

    public static class Property {
        @JsonProperty("PropertyId")
        private int propertyId;

        @JsonProperty("PropertyLevel")
        private int propertyLevel;

        @JsonProperty("PropertyValue")
        private int propertyValue;

        public int getPropertyId() {
            return this.propertyId;
        }

        public int getPropertyLevel() {
            return this.propertyLevel;
        }

        public int getPropertyValue() {
            return this.propertyValue;
        }
    }

    @Override
    public int getTtl() {
        return this.ttl;
    }

    public String getUid() {
        return this.uid;
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }

    /**
     * Converts this object to {@link ZZZUserInformation}
     * <br>Please read the documentation of this class to understand why you <i>might</i> want this.
     * @return A {@link ZZZUserInformation} object.
     */
    @NotNull
    public ZZZUserInformation toZenlessUser() {
        return ZZZUserInformation.fromEnkaUser(this);
    }
}