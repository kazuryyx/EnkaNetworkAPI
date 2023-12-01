package me.kazury.enkanetworkapi.util;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds 2 values.
 * <br>Similar to a {@link java.util.Map.Entry} but with a different name.
 * @param <K> first value
 * @param <V> second value
 */
public class Pair<K, V> {
    @NotNull
    private final K first;

    @NotNull
    private final V second;

    public Pair(@NotNull K first, @NotNull V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return First value of the pair.
     */
    @NotNull
    public K getFirst() {
        return this.first;
    }

    /**
     * @return Second value of the pair.
     */
    @NotNull
    public V getSecond() {
        return this.second;
    }
}
