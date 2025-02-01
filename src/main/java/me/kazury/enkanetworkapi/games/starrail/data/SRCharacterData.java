package me.kazury.enkanetworkapi.games.starrail.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.games.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

/**
 * This class is different from {@link SRUserCharacter}
 * <br>This class is used to get the character data from the game.
 * <br>While {@link SRUserCharacter} is used to get the character data from the user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRCharacterData {
    private String characterId;

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
     * Sets the character id for this object
     */
    public void setCharacterId(@NotNull String characterId) {
        if (this.characterId != null) throw new UnsupportedOperationException("Internal method call");
        this.characterId = characterId;
    }

    /**
     * The amount of stars this character has.
     * <br>This is either a value of 4 or 5.
     */
    public int getStar() {
        return this.star;
    }

    /**
     * @return The id of the character.
     */
    @NotNull
    public String getCharacterId() {
        if (this.characterId == null) throw new UnsupportedOperationException("Method called too early or not loaded");
        return this.characterId;
    }

    /**
     * The path that this character belongs to, this is only the internal name, so you will need to check from the list below.
     * <ul>
     *     <li>Knight = Preservation</li>
     *     <li>Mage = Erudition</li>
     *     <li>Priest = Abundance</li>
     *     <li>Rogue = The Hunt</li>
     *     <li>Shaman = Harmony</li>
     *     <li>Warlock = Nihility</li>
     *     <li>Warrior = Destruction</li>
     *     <li>Memory = Remembrance</li>
     * </ul>
     */
    @NotNull
    @Deprecated
    public String getPath() {
        return this.path;
    }

    /**
     * The path that this character belongs to.
     */
    @NotNull
    public SRCharacterPath getCharacterPath() {
        return SRCharacterPath.fromInternalData(this.path.toUpperCase());
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
     * @deprecated This was deprecated in 2.1 (29th April 2024) since nobody uses this for their name.
     *             <br>Maybe in the future this will not be deprecated anymore.
     * @see #getAvatarNameData()
     */
    @Deprecated
    @NotNull
    public AvatarFullName getAvatarFullNameData() {
        return this.avatarFullNameData;
    }

    /**
     * The <a href="https://honkai-star-rail.fandom.com/wiki/Type">Element</a> of the character.
     */
    @NotNull
    public String getElement() {
        return this.element;
    }

    /**
     * The round icon of this character.
     * <br>You will need to parse this yourself with {@link EnkaNetworkAPI#getSRIcon(String)}
     */
    @NotNull
    public String getAvatarRoundIcon() {
        return "SpriteOutput/AvatarRoundIcon/" + this.characterId + ".png";
    }

    /**
     * The icon of this character when they are looking to the left.
     * <br>You will need to parse this yourself with {@link EnkaNetworkAPI#getSRIcon(String)}
     */
    @NotNull
    public String getAvatarSideIcon() {
        return this.avatarSideIcon;
    }

    /**
     * Represents the splash art of the character, this is the image that is displayed when you pulled the character.
     * <br>You will need to parse this yourself with {@link EnkaNetworkAPI#getSRIcon(String)}
     */
    @NotNull
    public String getSplashArt() {
        return this.avatarCutinFront;
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
