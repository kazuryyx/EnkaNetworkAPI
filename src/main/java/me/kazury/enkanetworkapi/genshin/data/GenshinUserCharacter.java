package me.kazury.enkanetworkapi.genshin.data;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * This class is different from {@link GenshinCharacterData}
 * <br>This class is used to get the character data from the user.
 * <br>While {@link GenshinCharacterData} is used to get the character data from the game.
 */
public class GenshinUserCharacter {
    private final int id;
    private final int constellation;
    private final int currentExperience;
    private final int currentAscension;
    private final int currentLevel;
    private final int friendshipLevel;

    @NotNull
    private final Map<GenshinFightProp, Double> fightProperties;

    @NotNull
    private final GenshinCharacterTalents talentLevels;

    @NotNull
    private final List<GenshinArtifact> artifacts;

    @NotNull
    private final GenshinUserWeapon equippedWeapon;

    public GenshinUserCharacter(final int id,
                                final int constellation,
                                final int currentExperience,
                                final int currentAscension,
                                final int currentLevel,
                                final int friendshipLevel,
                                @NotNull Map<GenshinFightProp, Double> fightProperties,
                                @NotNull GenshinCharacterTalents talentLevels,
                                @NotNull List<GenshinArtifact> artifacts,
                                @NotNull GenshinUserWeapon equippedWeapon) {
        this.id = id;
        this.constellation = constellation;
        this.currentExperience = currentExperience;
        this.currentAscension = currentAscension;
        this.currentLevel = currentLevel;
        this.friendshipLevel = friendshipLevel;
        this.fightProperties = fightProperties;
        this.talentLevels = talentLevels;
        this.artifacts = artifacts;
        this.equippedWeapon = equippedWeapon;
    }

    /**
     * The id of the character.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the max level this character can currently be leveled up to.
     */
    public int getMaxLevel() {
        final int[] promotions = new int[]{20, 40, 50, 60, 70, 80, 90};
        return promotions[this.currentAscension];
    }

    /**
     * Represents the constellation level of the character.
     */
    public int getConstellation() {
        return this.constellation;
    }

    /**
     * Represents the current experience this character has for the next level.
     * <br>If the character cannot collect experience (due to not ascending it), then this will be -1.
     */
    public int getCurrentExperience() {
        return this.currentExperience;
    }

    /**
     * Represents the current ascension of the character.
     * <br>For example, if the character is ascended to level 90, this will be 6.
     */
    public int getCurrentAscension() {
        return this.currentAscension;
    }

    /**
     * Represents the current level of the character.
     * <br>This is a range from 0 to 90.
     */
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Returns the friendship level of this character
     * <br>Friendship can be raised by completing daily quests, random events, and more.
     * <br>Friendship can be used to unlock voice lines, and a (cute) namecard.
     * <br>This is a range from 0 to 10.
     */
    public int getFriendshipLevel() {
        return this.friendshipLevel;
    }

    /**
     * Represents a map of the character's properties.
     * <br>For example, the character's level, health, attack, etc.
     * @see GenshinFightProp
     */
    @NotNull
    public Map<GenshinFightProp, Double> getFightProperties() {
        return this.fightProperties;
    }

    /**
     * Represents the character's skill levels (in-game called talents).
     * <br>For example, the character's normal attack, elemental skill, and elemental burst.
     */
    @NotNull
    public GenshinCharacterTalents getTalentLevels() {
        return this.talentLevels;
    }

    /**
     * Represents an artifact that the character currently has equipped
     * <br>A character can have up to 5 artifacts equipped, including 4 sub stats on each.
     * @see GenshinArtifact
     * @see GenshinArtifactType
     */
    @NotNull
    public List<GenshinArtifact> getArtifacts() {
        return this.artifacts;
    }

    /**
     * The weapon that this character currently has equipped.
     * @see GenshinUserWeapon
     */
    @NotNull
    public GenshinUserWeapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

    /**
     * @see GenshinCharacterData
     * @throws NullPointerException if the library version is not the version of genshin.
     * @return The game data of this character, you can use this to get costume info, character name, and much more that this class does not provide.
     */
    @NotNull
    public GenshinCharacterData getGameData() {
        final GenshinCharacterData data = EnkaCaches.getGenshinCharacterData(String.valueOf(this.id));
        if (data == null) throw new UpdateLibraryException();
        return data;
    }

    /**
     * @return A builder for this class.
     */
    @NotNull
    public static GenshinUserCharacterBuilder builder() {
        return new GenshinUserCharacterBuilder();
    }

    public static class GenshinUserCharacterBuilder {
        private int id;
        private int constellation;
        private int currentExperience;
        private int currentAscension;
        private int currentLevel;
        private int friendshipLevel;
        private Map<GenshinFightProp, Double> fightProperties;
        private GenshinCharacterTalents talentLevels;
        private List<GenshinArtifact> artifacts;
        private GenshinUserWeapon equippedWeapon;

        @NotNull
        public GenshinUserCharacterBuilder id(final int id) {
            this.id = id;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder constellation(final int constellation) {
            this.constellation = constellation;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder currentExperience(final int currentExperience) {
            this.currentExperience = currentExperience;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder currentAscension(final int currentAscension) {
            this.currentAscension = currentAscension;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder currentLevel(final int currentLevel) {
            this.currentLevel = currentLevel;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder friendshipLevel(final int friendshipLevel) {
            this.friendshipLevel = friendshipLevel;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder fightProperties(@NotNull Map<GenshinFightProp, Double> fightProperties) {
            this.fightProperties = fightProperties;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder talentLevels(@NotNull GenshinCharacterTalents talentLevels) {
            this.talentLevels = talentLevels;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder artifacts(@NotNull List<GenshinArtifact> artifacts) {
            this.artifacts = artifacts;
            return this;
        }

        @NotNull
        public GenshinUserCharacterBuilder equippedWeapon(@Nullable GenshinUserWeapon equippedWeapon) {
            this.equippedWeapon = equippedWeapon;
            return this;
        }

        @NotNull
        public GenshinUserCharacter build() {
            return new GenshinUserCharacter(
                    this.id,
                    this.constellation,
                    this.currentExperience,
                    this.currentAscension,
                    this.currentLevel,
                    this.friendshipLevel,
                    this.fightProperties,
                    this.talentLevels,
                    this.artifacts,
                    this.equippedWeapon
            );
        }
    }
}
