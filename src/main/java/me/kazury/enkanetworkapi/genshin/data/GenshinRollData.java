package me.kazury.enkanetworkapi.genshin.data;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * A class which represents the efficiency of an artifact.
 */
@Data
public class GenshinRollData {
    /**
     * A map which has an affix id as key, and the value (efficiency) as value.
     * <br>You can use this method to get the efficiency of rolls, and possibly create a rating system.
     */
    private final Map<Integer, Double> efficiencyList;
    /**
     * A list of append prop ids. (aka the affix ids)
     */
    private final List<Integer> appendPropIds;
}
