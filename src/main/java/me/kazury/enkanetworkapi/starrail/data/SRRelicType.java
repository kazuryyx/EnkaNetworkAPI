package me.kazury.enkanetworkapi.starrail.data;

import org.jetbrains.annotations.Nullable;

/**
 * A relic type that is used for identifying the relic type
 * @see SRRelic
 */
public enum SRRelicType {
    HEAD,
    HAND,
    BODY,
    FEET,
    PLANAR,
    ROPE;

    @Nullable
    public static SRRelicType fromId(final int id) {
        for (SRRelicType type : values()) {
            if ((type.ordinal() + 1) == id) {
                return type;
            }
        }
        return null;
    }
}
