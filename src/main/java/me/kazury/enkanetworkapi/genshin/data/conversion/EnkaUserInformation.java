package me.kazury.enkanetworkapi.genshin.data.conversion;

import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.data.GenshinUserInformation;
import me.kazury.enkanetworkapi.genshin.util.IExpiryTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * This class is used for converting json to this class.
 * <br>You should never prefer using this class and instead use {@link GenshinUserInformation}
 * <br>This class contains a lot of nullability, and will cause issues if you try to use it.
 * <br>If you are wondering what to use in alternative, take a look at {@link GenshinUserInformation#fromEnkaUser(EnkaUserInformation)}.
 * @see GenshinUserInformation
 */
@Getter
public class EnkaUserInformation implements IExpiryTime {
    private PlayerInfo playerInfo;
    private List<AvatarInfo> avatarInfoList;
    private int ttl;
    private String uid;

    @Getter
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
    }

    @Getter
    public static class AvatarInfo {
        private int avatarId; // character id
        private Map<String, Object> propMap;
        private List<Integer> talentIdList;
        private Map<String, Double> fightPropMap;
        private int skillDepotId;
        private List<Integer> inherentProudSkillList;

        private List<EquipData> equipList;
        private FetterInfo fetterInfo;
    }

    @Getter
    public static class ShowAvatarInfo {
        private int avatarId;
        private int level;
        private int costumeId;
    }

    @Getter
    public static class FetterInfo {
        private int expLevel;
    }

    @Getter
    public static class EquipData {
        private long itemId; // always there
        private ArtifactData reliquary; // artifacts, null if weapon
        private WeaponData weapon; // weapons, null if artifacts

        private FlatData flat; // always there
    }

    @Getter
    public static class ArtifactData {
        private int level;
        private int mainPropId;
        private List<Integer> appendPropIdList;
    }

    @Getter
    public static class FlatData {
        private String nameTextMapHash; // weapons, artifacts
        private String setNameTextMapHash; // only exists on artifacts
        private int rankLevel; // weapons, artifacts
        private List<SubData> weaponStats; // weapons, null if artifacts
        private ArtifactMainData reliquaryMainstat; // artifacts, null if weapon
        private List<SubData> reliquarySubstats; // artifacts, null if weapon
        private String icon; // weapons, artifacts
        private String equipType; // artifacts, null if weapon
    }

    @Getter
    public static class WeaponData {
        private int level;
        private int promoteLevel;
        private Map<String, Integer> affixMap;
    }

    @Getter
    public static class ArtifactMainData {
        private String mainPropId;
        private double statValue;
    }

    @Getter
    public static class SubData {
        private String appendPropId;
        private double statValue;
    }

    @Getter
    public static class ProfilePicture {
        private long avatarId;
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
