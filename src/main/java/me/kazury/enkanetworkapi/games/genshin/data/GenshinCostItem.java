package me.kazury.enkanetworkapi.games.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A cost item for ascension.
 * <br>Used by {@link GenshinCharacterAscension} & {@link GenshinWeaponAscension} & {@link GenshinProudSkillData}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinCostItem {
    @JsonProperty("id")
    private int id;

    @JsonProperty("count")
    private int count;

    public GenshinCostItem() {}

    /**
     * Gets the material id of this item, this is used to parse the material.
     */
    public int getMaterialId() {
        return this.id;
    }

    /**
     * Gets the amount needed of the material.
     */
    public int getCount() {
        return this.count;
    }
}
