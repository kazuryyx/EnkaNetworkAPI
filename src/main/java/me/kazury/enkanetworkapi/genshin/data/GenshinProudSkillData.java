package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A proud skill data class that represents ProudSkillExcelConfigData.json.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinProudSkillData {
    @JsonProperty("proudSkillGroupId")
    private long proudSkillGroupId;

    @JsonProperty("level")
    private int level;

    @JsonProperty("coinCost")
    private int coinCost;

    @JsonProperty("costItems")
    private List<GenshinCostItem> costItems;

    private GenshinProudSkillData() {}

    /**
     * The proud skill group id of this object
     */
    public long getProudSkillGroupId() {
        return this.proudSkillGroupId;
    }

    /**
     * The level of this proud skill data
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The amount of mora it takes to upgrade this talent's level
     */
    public int getMoraCost() {
        return this.coinCost;
    }

    /**
     * The cost items required to upgrade this talent.
     */
    @NotNull
    public List<GenshinCostItem> getCostItems() {
        return Objects.requireNonNullElse(this.costItems, Collections.emptyList());
    }
}
