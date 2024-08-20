package me.kazury.enkanetworkapi.util.calculator.genshin;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaVerifier;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.util.calculator.BaseCalculator;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * A class that can calculate the required artifact cost for levelling up an artifact
 */
public class GenshinArtifactCalculator implements BaseCalculator<GenshinAscensionMaterial> {
    public static final int MAX_ARTIFACT_LEVEL = 20;

    private final int currentArtifactLevel;
    private final int targetArtifactLevel;
    private final int currentArtifactRarity;

    public GenshinArtifactCalculator(final int currentArtifactLevel,
                                     final int targetArtifactLevel,
                                     final int currentArtifactRarity) {
        this.currentArtifactLevel = currentArtifactLevel;
        this.targetArtifactLevel = targetArtifactLevel;
        this.currentArtifactRarity = currentArtifactRarity;
    }

    /**
     * Calculates the amount of experience needed to level up an artifact from level x to level y.
     */
    @Override
    @NotNull
    public List<GenshinAscensionMaterial> calculate() {
        if (this.currentArtifactLevel == MAX_ARTIFACT_LEVEL) {
            return Collections.emptyList();
        }

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
        return List.of(new GenshinAscensionMaterial(null, sum));
    }
}
