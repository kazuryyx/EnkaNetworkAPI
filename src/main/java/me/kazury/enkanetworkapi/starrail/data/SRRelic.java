package me.kazury.enkanetworkapi.starrail.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * TODO - Add documentation
 */
@Builder
@Getter
public class SRRelic implements SRNameable {
    /**
     * TODO - Add documentation
     */
    private int level;
    /**
     * TODO - Add documentation
     */
    private SRRelicType type;
    /**
     * TODO - Add documentation
     */
    private RelicStat mainStat;
    /**
     * TODO - Add documentation
     */
    private List<RelicStat> subStats;
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
    @Getter
    @AllArgsConstructor
    public static class RelicStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
    }
}
