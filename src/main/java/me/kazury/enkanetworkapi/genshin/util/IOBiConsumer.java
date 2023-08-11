package me.kazury.enkanetworkapi.genshin.util;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * A functional interface which is used for HTTP requests to the API
 * @param <T> the first parameter type
 * @param <R> the second parameter type
 */
@FunctionalInterface
public interface IOBiConsumer<T, R> {
    void accept(@NotNull T t, @NotNull R r) throws IOException;
}
