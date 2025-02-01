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
public class GenshinCharacterAscension {
    @JsonProperty("avatarPromoteId")
    private int promoteId;

    @JsonProperty("promoteLevel")
    private int promoteLevel;

    @JsonProperty("scoinCost")
    private int moraCost;

    @JsonProperty("costItems")
    private List<GenshinCostItem> costItems;

    public GenshinCharacterAscension() {}

    /**
     * Gets the promote id. This is retrieved by {@link GenshinCharacterConfig#getAvatarPromoteId()}.
     */
    public int getPromoteId() {
        return this.promoteId;
    }

    /**
     * Gets the promote level for this character. Also known as "ascension level".
     */
    public int getPromoteLevel() {
        return this.promoteLevel;
    }

    /**
     * Gets the amount of mora needed to ascend this character.
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
