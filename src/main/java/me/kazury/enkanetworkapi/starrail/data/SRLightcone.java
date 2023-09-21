package me.kazury.enkanetworkapi.starrail.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * TODO - Add documentation
 */
@Getter
@Builder
public class SRLightcone implements SRNameable {
    /**
     * TODO - Add documentation
     */
    private int superImposion;
    /**
     * TODO - Add documentation
     */
    private int level;
    /**
     * TODO - Add documentation
     */
    private int promotion;
    /**
     * TODO - Add documentation
     */
    private List<LightconeStat> stats;
    /**
     * TODO - Add documentation
     */
    private long hash;

    @Override
    @NotNull
    public String getNameHash() {
        return String.valueOf(this.hash);
    }

    /**
     * TODO - Add documentation
     */
    @Data
    public static class LightconeStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
    }
}
