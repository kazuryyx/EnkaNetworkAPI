package me.kazury.enkanetworkapi.starrail.data;

/**
 * A class representing a skill tree element in Honkai: Star Rail.
 */
public class SRSkillTreeElement {
    private final int pointId;
    private final int level;

    public SRSkillTreeElement(final int pointId, final int level) {
        this.pointId = pointId;
        this.level = level;
    }

    /**
     * The id of the skill tree element.
     */
    public int getPointId() {
        return pointId;
    }

    /**
     * The level of the skill tree element.
     */
    public int getLevel() {
        return level;
    }
}
