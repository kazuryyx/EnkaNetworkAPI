package me.kazury.enkanetworkapi.games.genshin.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * Represents a users characters talent levels.
 * <br>Extra talent levels (from Constellation & Childe) are not included.
 * <br>Will always be on range from 1-10.
 */
public class GenshinCharacterTalents {
    private final int normalAttackLevel;
    private final int elementalSkillLevel;
    private final int elementalBurstLevel;

    public GenshinCharacterTalents(@NotNull List<Integer> talents) {
        if (talents.size() == 1) {
            // element-less traveler
            this.normalAttackLevel = talents.get(0);
            this.elementalSkillLevel = 0;
            this.elementalBurstLevel = 0;
            return;
        }

        this.normalAttackLevel = talents.get(0);
        this.elementalSkillLevel = talents.get(1);
        this.elementalBurstLevel = talents.get(2);
    }

    /**
     * @return The normal attack level of this character.
     */
    public int getNormalAttackLevel() {
        return this.normalAttackLevel;
    }

    /**
     * @return The elemental skill level of this character.
     */
    public int getElementalSkillLevel() {
        return this.elementalSkillLevel;
    }

    /**
     * @return The elemental burst level of this character.
     */
    public int getElementalBurstLevel() {
        return this.elementalBurstLevel;
    }
}
