package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.util.GenshinNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * This class is different from {@link GenshinUserCharacter}
 * <br>This class is used to get the character data from the game.
 * <br>While {@link GenshinUserCharacter} is used to get the character data from the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinCharacterData implements GenshinNameable {
    private String characterId;

    @JsonProperty("Element")
    private String element;

    @JsonProperty("Consts")
    private List<String> consts;

    @JsonProperty("SkillOrder")
    private List<Integer> skillOrder;

    @JsonProperty("Skills")
    private Map<Integer, String> skills;

    @JsonProperty("NameTextMapHash")
    private long nameTextMapHash;

    @JsonProperty("SideIconName")
    private String sideIconName;

    @JsonProperty("WeaponType")
    private String weaponType;

    @JsonProperty("Rarity")
    private int star;

    @JsonProperty("Costumes")
    private Map<Integer, Costume> costumes;

    public GenshinCharacterData() {}

    /**
     * Sets the character id for this object
     */
    public void setCharacterId(@NotNull String characterId) {
        if (this.characterId != null) throw new UnsupportedOperationException("Internal method call");
        this.characterId = characterId;
    }

    @Override
    @NotNull
    public String getNameTextMapHash() {
        return String.valueOf(this.nameTextMapHash);
    }

    /**
     * Returns the icon of the character when the character is looking at the camera.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * @return At camera looking icon
     */
    @NotNull
    public String getIconName() {
        return this.getSideIconName().replace("_Side", "");
    }

    /**
     * The splash art of the character when pulled in the gacha.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * @return The splash art of the character
     */
    @NotNull
    public String getSplashArt() {
        return this.getIconName().replace("UI_AvatarIcon", "UI_Gacha_AvatarImg");
    }

    /**
     * The image at the end of a 10-pull
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * @return The image at the end of a 10-pull
     */
    @NotNull
    public String getGachaImage() {
        return this.getIconName().replace("UI_AvatarIcon", "UI_Gacha_AvatarIcon");
    }

    /**
     * The <a href="https://genshin-impact.fandom.com/wiki/Element">Element</a> of the character.
     */
    @NotNull
    public String getElement() {
        return this.element;
    }

    /**
     * The image ids of the characters talents.
     * <br>This includes Normal Attack, Elemental Skill, Elemental Burst and Passive Talents.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    @NotNull
    public List<String> getConsts() {
        return this.consts;
    }

    /**
     * The skill order in which the talents should be leveled up.
     * <br>This will return a list of integers, which represents the talent order. (NA, ES, EB)
     * <br>If you want to get the image of the talent then take a look at the skills map.
     */
    @NotNull
    public List<Integer> getSkillOrder() {
        return this.skillOrder;
    }

    /**
     * Represents a map of Integer (Talent Id from skillOrder), String (Talent UI Image)
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    @NotNull
    public Map<Integer, String> getSkills() {
        return this.skills;
    }

    /**
     * Represents the UI Element of the side icon.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * <br>This is the icon that is used on genshin's right side to switch characters.
     */
    @NotNull
    public String getSideIconName() {
        return this.sideIconName;
    }

    /**
     * Represents the weapon type of this character.
     * <ul>
     *     <li>WEAPON_SWORD_ONE_HAND = Sword</li>
     *     <li>WEAPON_CATALYST = Catalyst</li>
     *     <li>WEAPON_CLAYMORE = Claymore (2 hands)</li>
     *     <li>WEAPON_BOW = Bow</li>
     *     <li>WEAPON_POLE = Polearm</li>
     * </ul>
     */
    @NotNull
    public String getWeaponType() {
        return this.weaponType;
    }

    /**
     * Gets the character's rarity.
     * <br> For example, Furina is a 5-star, while Fischl is a 4-star.
     */
    public int getStar() {
        return star;
    }

    /**
     * Represents a map of Integer (Costume Id), Costume (Costume Data)
     * <br> Note that not every character has a costume and that the costume id is not the same as the character id.
     */
    @NotNull
    public Map<Integer, Costume> getCostumes() {
        return this.costumes;
    }

    /**
     * A custome from this character
     * <br>Costumes can be obtained from an event, and with genesis crystals (paid currency)
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Costume {
        @JsonProperty("sideIconName")
        private String sideIconName;

        @JsonProperty("icon")
        private String icon;

        @JsonProperty("art")
        private String art;

        public Costume() {}

        /**
         * Represents the UI Element of the side icon.
         * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
         */
        @NotNull
        public String getSideIconName() {
            return this.sideIconName;
        }

        /**
         * Represents the Icon when the character is looking at the camera.
         * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
         */
        @NotNull
        public String getIcon() {
            return this.icon;
        }

        /**
         * Represents the splash art of the character (with outfit equipped)
         * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
         */
        @NotNull
        public String getSplashArt() {
            return this.art;
        }
    }
}
