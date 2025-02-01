package me.kazury.enkanetworkapi.games.starrail.data;

import org.jetbrains.annotations.NotNull;

/**
 * Append prop data for Honkai: Star Rail.
 */
public class SRAppendPropData {
    private final SRAppendProp appendProp;
    private final String formattedValue;
    private final double rawValue;

    public SRAppendPropData(@NotNull SRAppendProp appendProp,
                            @NotNull String formattedValue,
                            final double rawValue) {
        this.appendProp = appendProp;
        this.formattedValue = formattedValue;
        this.rawValue = rawValue;
    }

    /**
     * The append prop this data belongs to.
     */
    @NotNull
    public SRAppendProp getAppendProp() {
        return this.appendProp;
    }

    /**
     * The formatted value, for example, for percentage stats 100.0 will be converted to 100%
     */
    @NotNull
    public String getFormattedValue() {
        return this.formattedValue;
    }

    /**
     * The raw value, 100.0 for example
     */
    public double getRawValue() {
        return this.rawValue;
    }
}
