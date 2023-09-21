package me.kazury.enkanetworkapi.starrail.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A lightcone that is currently equipped on a {@link SRUserCharacter}
 */
@Getter
@Builder
public class SRLightcone implements SRNameable {
    /**
     * The superImposion of this lightcone.
     * <br>Genshin terms: Refinement
     */
    private int superImposion;
    /**
     * The level of this lightcone.
     * <br>From 1 to 80.
     */
    private int level;
    /**
     * The promotion of this lightcone, this is also called the ascension.
     */
    private int promotion;
    /**
     * The stats that this lightcone has.
     */
    private List<LightconeStat> stats;
    /**
     * Represents a localization key for the weapon name.
     */
    private long hash;

    @Override
    @NotNull
    public String getNameHash() {
        return String.valueOf(this.hash);
    }

    /**
     * A lightcone stat, this is either the main stat, or the second stat.
     */
    @Data
    public static class LightconeStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
    }
}
