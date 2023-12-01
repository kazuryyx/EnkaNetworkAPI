package me.kazury.enkanetworkapi.genshin.data;

/**
 * Represents a users characters talent levels.
 * <br>Extra talent levels (from Constellation & Childe) are not included.
 * <br>Will always be on range from 1-10.
 */
public class GenshinCharacterTalents {
    private final int normalAttackLevel;
    private final int elementalSkillLevel;
    private final int elementalBurstLevel;

    public GenshinCharacterTalents(final int normalAttackLevel,
                                   final int elementalSkillLevel,
                                   final int elementalBurstLevel) {
        this.normalAttackLevel = normalAttackLevel;
        this.elementalSkillLevel = elementalSkillLevel;
        this.elementalBurstLevel = elementalBurstLevel;
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
