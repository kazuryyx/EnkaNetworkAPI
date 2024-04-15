package me.kazury.enkanetworkapi.util.calculator;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.enka.EnkaVerifier;
import me.kazury.enkanetworkapi.genshin.data.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A calculator object that calculates the amount of materials needed for ascension x-y.
 */
public class GenshinCalculator {
    public static final int MAX_ASCENSION = 6;
    public static final int MAX_ARTIFACT_LEVEL = 20;

    private final CalculatorType type;

    private int currentAscensionLevel;
    private int targetAscensionLevel;

    private int characterId;
    private long weaponId;

    private int currentArtifactLevel;
    private int currentArtifactRarity;
    private int targetArtifactLevel;

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
     * Calculates the amount of materials needed for ascension.
     */
    @NotNull
    public List<GenshinAscensionMaterial> calculate() {
        if (this.currentAscensionLevel == MAX_ASCENSION || this.currentArtifactLevel == MAX_ARTIFACT_LEVEL) {
            return Collections.emptyList();
        }

        return switch (this.type) {
            case CHARACTER -> {
                EnkaVerifier.verifyNotZero(this.targetAscensionLevel, this.characterId);

                final int promoteId = EnkaCaches.getGenshinAvatarConfigs()
                        .stream()
                        .filter(c -> c.getCharacterId() == this.characterId)
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new)

                        .getAvatarPromoteId();

                final List<GenshinCharacterAscension> ascensionData = EnkaCaches.getGenshinCharacterAscensions()
                        .stream()
                        .filter(a -> a.getPromoteId() == promoteId)
                        .toList();

                int moraSum = 0;
                final List<GenshinAscensionMaterial> items = new ArrayList<>();

                for (int i = this.currentAscensionLevel; i < this.targetAscensionLevel + 1; i++) {
                    final int finalI = i;

                    final GenshinCharacterAscension ascension = ascensionData
                            .stream()
                            .filter(a -> a.getPromoteLevel() == finalI)
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new);

                    moraSum += ascension.getMoraCost();

                    for (GenshinCostItem item : ascension.getCostItems()) {
                        final int id = item.getMaterialId();
                        if (id == 0) {
                            // hoyo why would you put objects in there with no data
                            continue;
                        }

                        final GenshinMaterial material = EnkaCaches.getMaterial(id);

                        items.add(new GenshinAscensionMaterial(material, item.getCount()));
                    }
                }

                final GenshinAscensionMaterial mora = new GenshinAscensionMaterial(EnkaCaches.getMaterial(202), moraSum);
                items.add(mora);

                yield items;
            }
            case ARTIFACT -> {
                EnkaVerifier.verifyNotZero(this.currentArtifactLevel, this.targetArtifactLevel);

                int sum = 0;

                // Internally, there is 21 levels for an artifact
                for (int i = this.currentArtifactLevel; i < this.targetArtifactLevel + 1; i++) {
                    final int finalI = i;

                    final GenshinArtifactRequirement requirement = EnkaCaches.getArtifactRequirements()
                            .stream()
                            .filter(r -> r.getRank() == this.currentArtifactRarity && r.getLevel() == finalI)
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new);

                    sum += requirement.getExp();
                }
                yield List.of(new GenshinAscensionMaterial(null, sum));
            }
            case WEAPON -> {
                EnkaVerifier.verifyNotZero(this.weaponId, this.targetAscensionLevel);

                final int promoteId = EnkaCaches.getGenshinWeaponConfigs()
                        .stream()
                        .filter(c -> c.getWeaponId() == this.weaponId)
                        .findFirst()
                        .orElseThrow(IllegalArgumentException::new)

                        .getWeaponPromoteId();

                final List<GenshinWeaponAscension> ascensionData = EnkaCaches.getGenshinWeaponAscensions()
                        .stream()
                        .filter(a -> a.getPromoteId() == promoteId)
                        .toList();

                int moraSum = 0;
                final List<GenshinAscensionMaterial> items = new ArrayList<>();

                for (int i = this.currentAscensionLevel; i < this.targetAscensionLevel + 1; i++) {
                    final int finalI = i;

                    final GenshinWeaponAscension ascension = ascensionData
                            .stream()
                            .filter(a -> a.getPromoteLevel() == finalI)
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new);

                    moraSum += ascension.getMoraCost();

                    for (GenshinCostItem item : ascension.getCostItems()) {
                        final int id = item.getMaterialId();
                        if (id == 0) {
                            // hoyo why would you put objects in there with no data
                            continue;
                        }

                        final GenshinMaterial material = EnkaCaches.getMaterial(id);

                        items.add(new GenshinAscensionMaterial(material, item.getCount()));
                    }
                }

                final GenshinAscensionMaterial mora = new GenshinAscensionMaterial(EnkaCaches.getMaterial(202), moraSum);
                items.add(mora);
                yield items;
            }
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
     * @param character  The character to calculate the ascension materials for.
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
     * @param artifact The artifact to calculate the ascension materials for.
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

    @NotNull
    public static GenshinCalculator createWeapon(@NotNull GenshinUserWeapon weapon,
                                                         final int wantedAscension) {
        return new GenshinCalculator(CalculatorType.WEAPON)
                .setWeaponId(weapon.getId())
                .setCurrentAscensionLevel(weapon.getWeaponAscension())
                .setTargetAscensionLevel(wantedAscension);
    }

    public enum CalculatorType {
        CHARACTER,
        ARTIFACT,
        WEAPON
    }
}
