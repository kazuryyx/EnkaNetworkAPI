package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
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
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinCharacterData implements GenshinNameable {
    /**
     * The <a href="https://genshin-impact.fandom.com/wiki/Element">Element</a> of the character.
     */
    @JsonProperty("Element")
    private String element;
    /**
     * The image ids of the characters talents.
     * <br>This includes Normal Attack, Elemental Skill, Elemental Burst and Passive Talents.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    @JsonProperty("Consts")
    private List<String> consts;
    /**
     * The skill order in which the talents should be leveled up.
     * <br>This will return a list of integers, which represents the talent order. (NA, ES, EB)
     * <br>If you want to get the image of the talent then take a look at the skills map.
     */
    @JsonProperty("SkillOrder")
    private List<Integer> skillOrder;
    /**
     * Represents a map of Integer (Talent Id from skillOrder), String (Talent UI Image)
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    @JsonProperty("Skills")
    private Map<Integer, String> skills;
    /**
     * Represents a localization key for the character name.
     */
    @JsonProperty("NameTextMapHash")
    private long nameTextMapHash;
    /**
     * Represents the UI Element of the side icon.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * <br>This is the icon that is used on genshin's right side to switch characters.
     */
    @JsonProperty("SideIconName")
    private String sideIconName;
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
    @JsonProperty("WeaponType")
    private String weaponType;
    /**
     * Gets the character's rarity.
     * <br> For example, Furina is a 5-star, while Fischl is a 4-star.
     */
    @JsonProperty("Rarity")
    private int star;

    /**
     * Represents a map of Integer (Costume Id), Costume (Costume Data)
     * <br> Note that not every character has a costume and that the costume id is not the same as the character id.
     */
    @JsonProperty("Costumes")
    private Map<Integer, Costume> costumes;

    public GenshinCharacterData() {}

    @Override
    @NotNull
    public String getNameTextMapHash() {
        return String.valueOf(this.nameTextMapHash);
    }

    /**
     * Returns the icon of the character when the character is looking at the camera.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * @apiNote In 4.1, the icon parsing was changed and HoYo. There is no need to use this method.
     *          <br>Some characters might not have this method after 4.1, and so it will be an empty image.
     * @return At camera looking icon
     */
    @NotNull
    public String getIconName() {
        return this.sideIconName.replace("_Side", "");
    }

    /**
     * A custome from this character
     * <br>Costumes can be obtained from an event, and with genesis crystals (paid currency)
     */
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Costume {
        /**
         * Represents the UI Element of the side icon.
         * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
         */
        @JsonProperty("sideIconName")
        private String sideIconName;
        /**
         * Represents the Icon when the character is looking at the camera.
         * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
         */
        @JsonProperty("icon")
        private String icon;
        /**
         * Represents the art of the character when the outfit was obtained
         * <br> You may have heard the term "splash art" before, this is the same thing.
         * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
         */
        @JsonProperty("art")
        private String art;

        public Costume() {}
    }
}
