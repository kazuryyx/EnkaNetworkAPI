package me.kazury.enkanetworkapi.util.calculator.genshin;

/**
 * A class which tells the {@link GenshinTalentCalculator} what talent levels you want.
 * <br>Do not enter talent levels >= 10. The bonus(es) from Childe / Constellations are not included in calculation.
 * <br>If you want a specific talent to not be calculated, then enter (1 to Current Level) and nothing above.
 */
public class GenshinTalentDesire {
    private final int wantedLevelAttack;
    private final int wantedLevelSkill;
    private final int wantedLevelBurst;

    public GenshinTalentDesire(final int wantedLevelAttack,
                               final int wantedLevelSkill,
                               final int wantedLevelBurst) {
        this.wantedLevelAttack = wantedLevelAttack;
        this.wantedLevelSkill = wantedLevelSkill;
        this.wantedLevelBurst = wantedLevelBurst;
    }

    /**
     * Gets the level that the NA (Normal Attack) should be
     */
    public int getWantedLevelAttack() {
        return this.wantedLevelAttack;
    }

    /**
     * Gets the level that the E (Elemental Skill) should be
     */
    public int getWantedLevelSkill() {
        return this.wantedLevelSkill;
    }

    /**
     * Gets the level that the Q (Elemental Burst) should be
     */
    public int getWantedLevelBurst() {
        return this.wantedLevelBurst;
    }
}
