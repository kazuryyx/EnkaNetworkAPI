package me.kazury.enkanetworkapi.genshin.util;

import org.jetbrains.annotations.NotNull;

/**
 * A functional interface which is used for accepting values
 */
@FunctionalInterface
public interface IValueAcceptor {
    /**
     * Accepts a value and formats this object by the given implementation
     * @param value the value to accept
     * @return the formatted value
     */
    @NotNull
    String accept(final double value);
}
