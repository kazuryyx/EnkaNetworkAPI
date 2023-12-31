package me.kazury.enkanetworkapi.starrail.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

/**
 * This class is different from {@link SRUserCharacter}
 * <br>This class is used to get the character data from the game.
 * <br>While {@link SRUserCharacter} is used to get the character data from the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRCharacterData {
    @JsonProperty("Rarity")
    private int star;

    @JsonProperty("AvatarBaseType")
    private String path;

    @JsonProperty("AvatarName")
    private AvatarName avatarNameData;

    @JsonProperty("AvatarFullName")
    private AvatarFullName avatarFullNameData;

    @JsonProperty("Element")
    private String element;

    @JsonProperty("AvatarSideIconPath")
    private String avatarSideIcon;

    @JsonProperty("AvatarCutinFrontImgPath")
    private String avatarCutinFront;

    public SRCharacterData() {}

    /**
     * The amount of stars this character has.
     * <br>This is either a value of 4 or 5.
     */
    public int getStar() {
        return this.star;
    }

    /**
     * The path that this character belongs to, this is only the internal name so you will need to check from the list below.
     * <ul>
     *     <li>Knight = Preservation</li>
     *     <li>Mage = Erudition</li>
     *     <li>Priest = Abundance</li>
     *     <li>Rogue = The Hunt</li>
     *     <li>Shaman = Harmony</li>
     *     <li>Warlock = Nihility</li>
     *     <li>Warrior = Destruction</li>
     * </ul>
     */
    @NotNull
    public String getPath() {
        return this.path;
    }

    /**
     * The Avatar Name that is displayed publicly.
     */
    @NotNull
    public AvatarName getAvatarNameData() {
        return this.avatarNameData;
    }

    /**
     * The full name of this character
     */
    @NotNull
    public AvatarFullName getAvatarFullNameData() {
        return avatarFullNameData;
    }

    /**
     * The <a href="https://honkai-star-rail.fandom.com/wiki/Type">Element</a> of the character.
     */
    @NotNull
    public String getElement() {
        return element;
    }

    /**
     * The icon of this character when they are looking to the left.
     * <br>You will need to parse this yourself with {@link EnkaNetworkAPI#getSRIcon(String)}
     */
    @NotNull
    public String getAvatarSideIcon() {
        return avatarSideIcon;
    }

    /**
     * Represents the art of the character when the character was obtained
     * <br> You may have heard the term "splash art" before, this is the same thing.
     * <br>You will need to parse this yourself with {@link EnkaNetworkAPI#getSRIcon(String)}
     */
    @NotNull
    public String getAvatarCutinFront() {
        return avatarCutinFront;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AvatarName implements SRNameable {
        /**
         * Represents a hash which is used for locale.
         */
        @JsonProperty("Hash")
        private long hash;

        public AvatarName() {}

        @Override
        @NotNull
        public String getNameHash() {
            return String.valueOf(this.hash);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AvatarFullName implements SRNameable {
        /**
         * Represents a hash which is used for locale.
         */
        @JsonProperty("Hash")
        private long hash;

        public AvatarFullName() {}

        @Override
        @NotNull
        public String getNameHash() {
            return String.valueOf(this.hash);
        }
    }
}
