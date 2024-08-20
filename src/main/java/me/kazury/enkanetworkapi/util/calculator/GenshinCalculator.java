package me.kazury.enkanetworkapi.util.calculator;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaVerifier;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.util.calculator.genshin.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * A calculator object that calculates the amount of materials needed for ascension/level x-y.
 */
public class GenshinCalculator {
    public static final int MAX_ASCENSION = 6;

    private final CalculatorType type;

    private int currentAscensionLevel;
    private int targetAscensionLevel;

    private int characterId;
    private long weaponId;

    private int currentArtifactLevel;
    private int currentArtifactRarity;
    private int targetArtifactLevel;

    private GenshinCharacterTalents currentTalents;
    private GenshinTalentDesire talentDesire;

    protected GenshinCalculator(@NotNull CalculatorType type) {
        this.type = type;
    }

    /**
     * Sets the current ascension level.
     */
    public GenshinCalculator setCurrentAscensionLevel(final int currentAscensionLevel) {
        this.currentAscensionLevel = currentAscensionLevel;
        return this;
    }

    /**
     * Sets the character id.
     */
    public GenshinCalculator setCharacterId(final int characterId) {
        this.characterId = characterId;
        return this;
    }

    /**
     * Sets the weapon id.
     */
    public GenshinCalculator setWeaponId(final long weaponId) {
        this.weaponId = weaponId;
        return this;
    }

    /**
     * Sets the target ascension level.
     */
    public GenshinCalculator setTargetAscensionLevel(final int targetAscensionLevel) {
        this.targetAscensionLevel = targetAscensionLevel;
        return this;
    }

    /**
     * Sets the current artifact level.
     */
    public GenshinCalculator setCurrentArtifactLevel(final int currentArtifactLevel) {
        this.currentArtifactLevel = currentArtifactLevel;
        return this;
    }

    /**
     * Sets the current artifact rarity.
     */
    public GenshinCalculator setCurrentArtifactRarity(final int currentArtifactRarity) {
        this.currentArtifactRarity = currentArtifactRarity;
        return this;
    }

    /**
     * Sets the target artifact level.
     */
    public GenshinCalculator setTargetArtifactLevel(final int targetArtifactLevel) {
        this.targetArtifactLevel = targetArtifactLevel;
        return this;
    }

    /**
     * Sets the current talents of this character
     */
    public GenshinCalculator setCurrentTalents(@NotNull GenshinCharacterTalents talents) {
        this.currentTalents = talents;
        return this;
    }

    /**
     * Sets the wanted talent levels.
     */
    public GenshinCalculator setTargetTalentDesire(@NotNull GenshinTalentDesire talentDesire) {
        this.talentDesire = talentDesire;
        return this;
    }

    /**
     * Calculates the amount of materials needed for ascension.
     */
    @NotNull
    public List<GenshinAscensionMaterial> calculate() {
        if (this.type.isCharacterRelated() && this.currentAscensionLevel == MAX_ASCENSION) {
            return Collections.emptyList();
        }

        return switch (this.type) {
            case CHARACTER -> new GenshinCharacterCalculator(this.characterId, this.currentAscensionLevel, this.targetAscensionLevel).calculate();
            case ARTIFACT -> new GenshinArtifactCalculator(this.currentArtifactLevel, this.targetArtifactLevel, this.currentArtifactRarity).calculate();
            case WEAPON -> new GenshinWeaponCalculator(this.weaponId, this.currentAscensionLevel, this.targetAscensionLevel).calculate();
            case CHARACTER_TALENTS -> new GenshinTalentCalculator(this.characterId, this.talentDesire, this.currentTalents).calculate();
        };
    }

    /**
     * Creates a new calculator object.
     */
    @NotNull
    public static GenshinCalculator create(@NotNull CalculatorType type) {
        return new GenshinCalculator(type);
    }


    /**
     * Creates a new character calculator object with the character and wanted ascension level.
     *
     * @param character       The character to calculate the ascension materials for.
     * @param wantedAscension The wanted ascension level.
     * @return A new calculator object.
     */
    @NotNull
    public static GenshinCalculator createCharacter(@NotNull GenshinUserCharacter character,
                                                    final int wantedAscension) {
        return new GenshinCalculator(CalculatorType.CHARACTER)
                .setCharacterId(character.getId())
                .setCurrentAscensionLevel(character.getCurrentAscension())
                .setTargetAscensionLevel(wantedAscension);
    }

    /**
     * Creates a new artifact calculator object with the artifact and wanted level.
     *
     * @param artifact    The artifact to calculate the ascension materials for.
     * @param wantedLevel The wanted artifact level.
     * @return A new calculator object.
     */
    @NotNull
    public static GenshinCalculator createArtifact(@NotNull GenshinArtifact artifact,
                                                   final int wantedLevel) {
        return new GenshinCalculator(CalculatorType.ARTIFACT)
                .setCurrentArtifactLevel(artifact.getLevel())
                .setCurrentArtifactRarity(artifact.getRankLevel())
                .setTargetArtifactLevel(wantedLevel);
    }

    /**
     * Creates a new weapon calculator object with the weapon
     *
     * @param weapon          The weapon to calculate the ascension materials for
     * @param wantedAscension The wanted ascension on this weapon
     * @return A new calculator object
     */
    @NotNull
    public static GenshinCalculator createWeapon(@NotNull GenshinUserWeapon weapon,
                                                 final int wantedAscension) {
        return new GenshinCalculator(CalculatorType.WEAPON)
                .setWeaponId(weapon.getId())
                .setCurrentAscensionLevel(weapon.getWeaponAscension())
                .setTargetAscensionLevel(wantedAscension);
    }

    /**
     * Creates a new talent calculator object with the character & your desired levels
     *
     * @param character    The character you want to calculate the materials for
     * @param talentDisire The class with desired talents levels for each type
     * @return A new calculator object
     */
    @NotNull
    public static GenshinCalculator createTalent(@NotNull GenshinUserCharacter character,
                                                 @NotNull GenshinTalentDesire talentDisire) {
        return new GenshinCalculator(CalculatorType.CHARACTER_TALENTS)
                .setCharacterId(character.getId())
                .setCurrentTalents(character.getTalentLevels())
                .setTargetTalentDesire(talentDisire);
    }

    public enum CalculatorType {
        CHARACTER(true),
        ARTIFACT(false),
        WEAPON(true),
        CHARACTER_TALENTS(false); // theoretically yes but in this context not

        private final boolean characterRelated;

        CalculatorType(final boolean characterRelated) {
            this.characterRelated = characterRelated;
        }

        /**
         * Returns if this character is related to a character action
         * <br>Mainly used for a check that checks if the current ascension level is the max, which applies for Characters and Weapons
         */
        public boolean isCharacterRelated() {
            return this.characterRelated;
        }
    }
}
