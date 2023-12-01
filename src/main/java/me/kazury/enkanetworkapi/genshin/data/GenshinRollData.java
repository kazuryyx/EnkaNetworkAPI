package me.kazury.enkanetworkapi.genshin.data;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * A class which represents the efficiency of an artifact.
 */
public class GenshinRollData {
    @NotNull
    private final Map<Integer, Double> efficiencyList;

    @NotNull
    private final List<Integer> appendPropIds;

    public GenshinRollData(@NotNull Map<Integer, Double> efficiencyList,
                           @NotNull List<Integer> appendPropIds) {
        this.efficiencyList = efficiencyList;
        this.appendPropIds = appendPropIds;
    }

    /**
     * A map which has an affix id as key, and the value (efficiency) as value.
     * <br>You can use this method to get the efficiency of rolls, and possibly create a rating system.
     */
    @NotNull
    public Map<Integer, Double> getEfficiencyList() {
        return this.efficiencyList;
    }

    /**
     * A list of append prop ids. (aka the affix ids)
     */
    @NotNull
    public List<Integer> getAppendPropIds() {
        return this.appendPropIds;
    }
}
