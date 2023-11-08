package me.kazury.enkanetworkapi.util;

import lombok.Data;

/**
 * A class that holds 2 values.
 * <br>Similar to a {@link java.util.Map.Entry} but with a different name.
 * @param <K> first value
 * @param <V> second value
 */
@Data
public class Pair<K, V> {
    private final K first;
    private final V second;
}
