package me.kazury.enkanetworkapi.util.calculator.genshin;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaVerifier;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinAscensionMaterial;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinCharacterAscension;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinCostItem;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinMaterial;
import me.kazury.enkanetworkapi.util.calculator.BaseCalculator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that can calculate what a characters needs for leveling it up
 */
public class GenshinCharacterCalculator implements BaseCalculator<GenshinAscensionMaterial> {
    private final int characterId;
    private final int currentAscensionLevel;
    private final int targetAscensionLevel;

    public GenshinCharacterCalculator(final int characterId,
                                      final int currentAscensionLevel,
                                      final int targetAscensionLevel) {
        this.characterId = characterId;
        this.currentAscensionLevel = currentAscensionLevel;
        this.targetAscensionLevel = targetAscensionLevel;
    }

    /**
     * Calculates the required materials for levelling this character from a specific ascension to another ascension.
     * <br>Does not include books, only materials!
     */
    @Override
    @NotNull
    public List<GenshinAscensionMaterial> calculate() {
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
                if (id == 0) continue;
                final GenshinMaterial material = EnkaCaches.getMaterial(id);

                items.add(new GenshinAscensionMaterial(material, item.getCount()));
            }
        }

        if (moraSum != 0) {
            final GenshinAscensionMaterial mora = new GenshinAscensionMaterial(EnkaCaches.getMaterial(202), moraSum);
            items.add(mora);
        }
        return items;
    }
}
