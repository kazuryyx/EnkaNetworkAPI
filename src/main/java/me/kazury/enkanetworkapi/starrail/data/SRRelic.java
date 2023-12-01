package me.kazury.enkanetworkapi.starrail.data;

import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A relic that is currently equipped on a {@link SRUserCharacter}.
 * <br>In genshin terms: Artifact.
 */
public class SRRelic implements SRNameable {
    private final int level;
    private final SRRelicType type;
    private final RelicStat mainStat;
    private final List<RelicStat> subStats;
    private final long hash;

    public SRRelic(final int level,
                   @Nullable SRRelicType type,
                   @NotNull RelicStat mainStat,
                   @NotNull List<RelicStat> subStats,
                   final long hash) {
        this.level = level;
        this.type = type;
        this.mainStat = mainStat;
        this.subStats = subStats;
        this.hash = hash;
    }

    /**
     * The level of this relic.
     * <br>From 0 to 15.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The relic type of this relic.
     * <br>There is 6 different types.
     * @see SRRelicType
     */
    public SRRelicType getType() {
        return this.type;
    }

    /**
     * The main stat of this relic.
     */
    public RelicStat getMainStat() {
        return this.mainStat;
    }

    /**
     * A list of this relic's sub stats.
     * <br>Every 3 levels of this relic, a substat is either unlocked or leveled up randomly.
     */
    public List<RelicStat> getSubStats() {
        return this.subStats;
    }

    /**
     * The hash of this artifact used for localization.
     * @see #getName()
     */
    @Override
    @NotNull
    public String getNameHash() {
        return String.valueOf(this.hash);
    }

    /**
     * A stat of this relic, this can either be the main stat, or a sub stat.
     */
    public static class RelicStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;

        public RelicStat(@NotNull String stat,
                         @NotNull String formattedValue,
                         final double rawValue) {
            this.stat = stat;
            this.formattedValue = formattedValue;
            this.rawValue = rawValue;
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
    }
}
