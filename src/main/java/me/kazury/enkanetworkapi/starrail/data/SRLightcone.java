package me.kazury.enkanetworkapi.starrail.data;

import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A lightcone that is currently equipped on a {@link SRUserCharacter}
 */
public class SRLightcone implements SRNameable {
    private final int superImposion;
    private final int level;
    private final int promotion;
    private final List<LightconeStat> stats;
    private final long hash;

    public SRLightcone(final int superImposion,
                       final int level,
                       final int promotion,
                       @NotNull List<LightconeStat> stats,
                       final long hash) {
        this.superImposion = superImposion;
        this.level = level;
        this.promotion = promotion;
        this.stats = stats;
        this.hash = hash;
    }

    /**
     * The superImposion of this lightcone.
     * <br>Genshin terms: Refinement
     */
    public int getSuperImposion() {
        return this.superImposion;
    }

    /**
     * The level of this lightcone.
     * <br>From 1 to 80.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The promotion of this lightcone, this is also called the ascension.
     */
    public int getPromotion() {
        return this.promotion;
    }

    /**
     * The stats that this lightcone has.
     */
    public List<LightconeStat> getStats() {
        return this.stats;
    }

    /**
     * Represents a localization key for the weapon name.
     */
    public long getHash() {
        return this.hash;
    }

    @Override
    @NotNull
    public String getNameHash() {
        return String.valueOf(this.hash);
    }

    /**
     * A lightcone stat, this is either the main stat, or the second stat.
     */
    public static class LightconeStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;

        public LightconeStat(@NotNull String stat,
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
