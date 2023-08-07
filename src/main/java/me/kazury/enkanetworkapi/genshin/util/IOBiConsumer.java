package me.kazury.enkanetworkapi.genshin.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@FunctionalInterface
public interface IOBiConsumer<T, R> {
    void accept(@NotNull T t, @NotNull R r) throws IOException;
}
