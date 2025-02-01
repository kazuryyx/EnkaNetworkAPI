package me.kazury.enkanetworkapi.games.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A requirement for leveling up an artifact.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinArtifactRequirement {
    @JsonProperty("rank")
    private int rank;

    @JsonProperty("level")
    private int level;

    @JsonProperty("exp")
    private int exp;

    public GenshinArtifactRequirement() {}

    /**
     * Gets the rarity of this artifact, from 1-5.
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Gets the level of this artifact, from 1-20.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the amount of exp needed to level up this artifact.
     */
    public int getExp() {
        return this.exp;
    }
}
