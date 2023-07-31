package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.exceptions.NoLocalizationFoundException;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * This class is different from {@link GenshinUserCharacter}
 * <br>This class is used to get the character data from the game.
 * <br>While {@link GenshinUserCharacter} is used to get the character data from the user.
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinCharacter {
    /**
     * The <a href="https://genshin-impact.fandom.com/wiki/Element">Element</a> of the character.
     */
    @JsonProperty("Element")
    private String element;
    /**
     * The image ids of the characters talents.
     * <br>This includes Normal Attack, Elemental Skill, Elemental Burst and Passive Talents.
     *
     * @see EnkaNetworkAPI#getIcon(String)
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
     */
    @JsonProperty("SideIconName")
    private String sideIconName;
    /**
     * Represents a map of Integer (Costume Id), Costume (Costume Data)
     * <br> Note that not every character has a costume and that the costume id is not the same as the character id.
     */
    @JsonProperty("Costumes")
    private Map<Integer, Costume> costumes;

    public GenshinCharacter() {}

    /**
     * Gets the name of the character given by a locale.
     * @param locale The locale.
     * @return The name of the character.
     * @throws NoLocalizationFoundException If the locale is {@code null} and the default localization is {@code null}.
     */
    public String getName(@Nullable GenshinLocalization locale) {
        locale = EnkaGlobals.parseLocalization(locale);
        return EnkaCaches.getLocale(locale, this.nameTextMapHash);
    }

    /**
     * Gets the name of the character given by the default locale.
     * <br>This method will fail if {@link EnkaNetworkAPI#setDefaultLocalization(GenshinLocalization)} was not called.
     * @return The name of the character.
     * @throws NoLocalizationFoundException If the default locale is {@code null}.
     */
    public String getName() {
        return this.getName(null);
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Costume {
        /**
         * Represents the UI Element of the side icon.
         */
        @JsonProperty("sideIconName")
        private String sideIconName;
        /**
         * Represents the Icon when the character is looking at the camera.
         */
        @JsonProperty("icon")
        private String icon;
        /**
         * Represents the art of the character when the outfit was obtained
         * <br> You may have heard the term "splash art" before, this is the same thing.
         */
        @JsonProperty("art")
        private String art;

        public Costume() {}
    }
}
