package me.kazury.enkanetworkapi.util.calculator.genshin;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaVerifier;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinAscensionMaterial;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinCostItem;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinMaterial;
import me.kazury.enkanetworkapi.games.genshin.data.GenshinWeaponAscension;
import me.kazury.enkanetworkapi.util.calculator.BaseCalculator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that can calculate weapon materials.
 */
public class GenshinWeaponCalculator implements BaseCalculator<GenshinAscensionMaterial> {
    private final long weaponId;
    private final int currentAscensionLevel;
    private final int targetAscensionLevel;

    public GenshinWeaponCalculator(final long weaponId,
                                   final int currentAscensionLevel,
                                   final int targetAscensionLevel) {
        this.weaponId = weaponId;
        this.currentAscensionLevel = currentAscensionLevel;
        this.targetAscensionLevel = targetAscensionLevel;
    }

    /**
     * Calculates the needed materials to level up this weapon from ascension x to ascension y.
     * <br>Does not include Enhancement Ores, only materials
     */
    @Override
    @NotNull
    public List<GenshinAscensionMaterial> calculate() {
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
