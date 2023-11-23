package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents a users characters talent levels.
 * <br>Extra talent levels (from Constellation & Childe) are not included.
 * <br>Will always be on range from 1-10.
 */
@AllArgsConstructor
@Getter
@Builder
public class GenshinCharacterTalents {
    private final int normalAttackLevel;
    private final int elementalSkillLevel;
    private final int elementalBurstLevel;
}
