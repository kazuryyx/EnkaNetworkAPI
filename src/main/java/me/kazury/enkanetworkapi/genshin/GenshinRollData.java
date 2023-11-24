package me.kazury.enkanetworkapi.genshin;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GenshinRollData {
    /**
     * TODO - Add documentation
     */
    private final Map<Integer, Double> efficiencyList;
    /**
     * TODO - Add documentation
     */
    private final List<Integer> appendPropIds;
}
