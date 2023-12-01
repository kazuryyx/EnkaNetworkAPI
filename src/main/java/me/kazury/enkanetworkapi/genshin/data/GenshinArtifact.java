package me.kazury.enkanetworkapi.genshin.data;

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
public class GenshinArtifact implements GenshinNameable {
    @NotNull
    private final GenshinArtifactType type;
    private final int level;

    @NotNull
    private final ArtifactStat mainStats;

    @NotNull
    private final List<Integer> appendPropIds;

    @NotNull
    private final List<ArtifactStat> subStats;

    @NotNull
    private final String setNameTextMapHash;

    @NotNull
    private final String icon;

    public GenshinArtifact(@NotNull GenshinArtifactType type,
                           final int level,
                           @NotNull ArtifactStat mainStats,
                           @NotNull List<Integer> appendPropIds,
                           @NotNull List<ArtifactStat> subStats,
                           @NotNull String setNameTextMapHash,
                           @NotNull String icon) {
        this.type = type;
        this.level = level;
        this.mainStats = mainStats;
        this.appendPropIds = appendPropIds;
        this.subStats = subStats;
        this.setNameTextMapHash = setNameTextMapHash;
        this.icon = icon;
    }

    /**
     * Represents the type of this artifact, this is either a flower, feather, sands, goblet, or a circlet.
     *
     * @see GenshinArtifactType
     */
    @NotNull
    public GenshinArtifactType getType() {
        return this.type;
    }

    /**
     * The level of the artifact, from 0 to 20.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The main stat of this artifact
     */
    @NotNull
    public ArtifactStat getMainStats() {
        return this.mainStats;
    }

    /**
     * A list of append prop ids.
     * <br>These are affix ids of an artifact.
     */
    @NotNull
    public List<Integer> getAppendPropIds() {
        return this.appendPropIds;
    }

    /**
     * All the substats of this artifact, there are 4 substats on one artifact
     */
    @NotNull
    public List<ArtifactStat> getSubStats() {
        return this.subStats;
    }

    /**
     * Represents a localization key for the artifact set name.
     */
    @NotNull
    public String getSetNameTextMapHash() {
        return this.setNameTextMapHash;
    }

    /**
     * The icon identifier of this artifact (flower icon, sands icon, etc.)
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    @NotNull
    public String getIcon() {
        return this.icon;
    }

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

    /**
     * @return A new builder for {@link GenshinArtifact}
     */
    @NotNull
    public static GenshinArtifactBuilder builder() {
        return new GenshinArtifactBuilder();
    }

    @Override
    @NotNull
    public String getNameTextMapHash() {
        return this.setNameTextMapHash;
    }

    /**
     * A stat on an artifact, this is either a main stat or a substat.
     */

    public static class ArtifactStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
        private final GenshinFightProp.ValueType valueType;

        public ArtifactStat(@NotNull String stat,
                            @NotNull String formattedValue,
                            final double rawValue,
                            @NotNull GenshinFightProp.ValueType valueType) {
            this.stat = stat;
            this.formattedValue = formattedValue;
            this.rawValue = rawValue;
            this.valueType = valueType;
        }

        @Override
        @NotNull
        public String getStat() {
            return this.stat;
        }

        @Override
        @NotNull
        public String getFormattedValue() {
            return this.formattedValue;
        }

        @Override
        public double getRawValue() {
            return this.rawValue;
        }

        /**
         * The value type of this artifact, this is either a flat value or a percentage value.
         */
        @NotNull
        public GenshinFightProp.ValueType getValueType() {
            return this.valueType;
        }
    }

    public static class GenshinArtifactBuilder {
        private GenshinArtifactType type;
        private int level;
        private ArtifactStat mainStats;
        private List<Integer> appendPropIds;
        private List<ArtifactStat> subStats;
        private String setNameTextMapHash;
        private String icon;

        @NotNull
        public GenshinArtifactBuilder type(@NotNull GenshinArtifactType type) {
            this.type = type;
            return this;
        }

        @NotNull
        public GenshinArtifactBuilder level(final int level) {
            this.level = level;
            return this;
        }

        @NotNull
        public GenshinArtifactBuilder mainStats(@NotNull ArtifactStat mainStats) {
            this.mainStats = mainStats;
            return this;
        }

        @NotNull
        public GenshinArtifactBuilder appendPropIds(@NotNull List<Integer> appendPropIds) {
            this.appendPropIds = appendPropIds;
            return this;
        }

        @NotNull
        public GenshinArtifactBuilder subStats(@NotNull List<ArtifactStat> subStats) {
            this.subStats = subStats;
            return this;
        }

        @NotNull
        public GenshinArtifactBuilder setNameTextMapHash(@NotNull String setNameTextMapHash) {
            this.setNameTextMapHash = setNameTextMapHash;
            return this;
        }

        @NotNull
        public GenshinArtifactBuilder icon(@NotNull String icon) {
            this.icon = icon;
            return this;
        }

        @NotNull
        public GenshinArtifact build() {
            return new GenshinArtifact(
                    this.type,
                    this.level,
                    this.mainStats,
                    this.appendPropIds,
                    this.subStats,
                    this.setNameTextMapHash,
                    this.icon
            );
        }
    }
}
