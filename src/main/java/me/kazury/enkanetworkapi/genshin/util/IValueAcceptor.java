package me.kazury.enkanetworkapi.genshin.util;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface IValueAcceptor {
    @NotNull
    String accept(final double value);
}
