package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.genshin.util.GenshinNameable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An artifact that is currently equipped on a {@link GenshinUserCharacter}.
 */
@Builder
@Getter
public class GenshinArtifact implements GenshinNameable {
    /**
     * Represents the type of this artifact, this is either a flower, feather, sands, goblet, or a circlet.
     *
     * @see GenshinArtifactType
     */
    private final GenshinArtifactType type;
    /**
     * The level of the artifact, from 0 to 20.
     */
    private final int level;

    /**
     * The main stat of this artifact
     */
    private ArtifactStat mainStats;

    /**
     * A list of append prop ids.
     * <br>These are affix ids of an artifact.
     */
    private List<Integer> appendPropIds;

    /**
     * All the substats of this artifact, there are 4 substats on one artifact
     */
    private List<ArtifactStat> subStats;

    /**
     * Represents a localization key for the artifact set name.
     */
    private String setNameTextMapHash;

    /**
     * The icon identifier of this artifact (flower icon, sands icon, etc.)
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    private String icon;

    /**
     * Scans the sub stat rolls of this artifact and calculates the efficiency between all the rolls.
     * @return Roll data of this artifact (scanning sub stats)
     */
    @NotNull
    public GenshinRollData getRollData(@NotNull EnkaNetworkAPI api) {
        final Map<Integer, Double> efficiencyMap = new HashMap<>();

        for (int id : this.getAppendPropIds()) {
            final GenshinAffix affix = api.getGenshinAffix(id);
            if (affix == null) {
                System.out.println("Unsupported affix: " + id + ". please redirect to library owner.");
                continue;
            }
            final double efficiency = Math.round(affix.getEfficiency() * 100.0) / 100.0;
            efficiencyMap.put(id, efficiencyMap.getOrDefault(id, 0.0) + efficiency);
        }
        return new GenshinRollData(efficiencyMap, this.getAppendPropIds());
    }

    @Override
    @NotNull
    public String getNameTextMapHash() {
        return this.setNameTextMapHash;
    }

    /**
     * A stat on an artifact, this is either a main stat or a substat.
     */
    @Getter
    @AllArgsConstructor
    public static class ArtifactStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
        /**
         * The value type of this artifact, this is either a flat value or a percentage value.
         */
        private GenshinFightProp.ValueType valueType;
    }
}
