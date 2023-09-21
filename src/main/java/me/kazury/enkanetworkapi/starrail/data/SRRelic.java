package me.kazury.enkanetworkapi.starrail.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.starrail.util.SRNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A relic that is currently equipped on a {@link SRUserCharacter}.
 * <br>In genshin terms: Artifact.
 */
@Builder
@Getter
public class SRRelic implements SRNameable {
    /**
     * The level of this relic.
     * <br>From 0 to 15.
     */
    private int level;
    /**
     * The relic type of this relic.
     * <br>There is 6 different types.
     * @see SRRelicType
     */
    private SRRelicType type;
    /**
     * The main stat of this relic.
     */
    private RelicStat mainStat;
    /**
     * A list of this relic's sub stats.
     * <br>Every 3 levels of this relic, a substat is either unlocked or leveled up randomly.
     */
    private List<RelicStat> subStats;
    /**
     * The hash of this artifact used for localization.
     * @see #getName()
     */
    private long hash;

    @Override
    @NotNull
    public String getNameHash() {
        return String.valueOf(this.hash);
    }

    /**
     * A stat of this relic, this can either be the main stat, or a sub stat.
     */
    @Getter
    @AllArgsConstructor
    public static class RelicStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
    }
}
