package me.kazury.enkanetworkapi.starrail.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * TODO - Add documentation
 */
@Getter
@AllArgsConstructor
public enum SRRelicType {
    HEAD(1),
    HAND(2),
    BODY(3),
    FEET(4),
    PLANAR(5),
    ROPE(6);

    private final int id;

    @Nullable
    public static SRRelicType fromId(int id) {
        for (SRRelicType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
