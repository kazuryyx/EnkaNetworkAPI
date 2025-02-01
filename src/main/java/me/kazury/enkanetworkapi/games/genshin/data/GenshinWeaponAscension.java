package me.kazury.enkanetworkapi.games.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An ascension data object. This is used to get the materials needed for ascension.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinWeaponAscension {
    @JsonProperty("weaponPromoteId")
    private int promoteId;

    @JsonProperty("promoteLevel")
    private int promoteLevel;

    @JsonProperty("coinCost")
    private int moraCost;

    @JsonProperty("costItems")
    private List<GenshinCostItem> costItems;

    public GenshinWeaponAscension() {}

    /**
     * Gets the promote id. This is retrieved by {@link GenshinUserWeapon#getId()}.
     */
    public int getPromoteId() {
        return this.promoteId;
    }

    /**
     * Gets the promote level for this weapon. Also known as "ascension level".
     */
    public int getPromoteLevel() {
        return this.promoteLevel;
    }

    /**
     * Gets the amount of mora needed to ascend this weapon.
     */
    public int getMoraCost() {
        return this.moraCost;
    }

    /**
     * Gets the cost items needed for ascension.
     */
    @NotNull
    public List<GenshinCostItem> getCostItems() {
        // first ascension has no items
        return Objects.requireNonNullElse(this.costItems, Collections.emptyList());
    }
}
