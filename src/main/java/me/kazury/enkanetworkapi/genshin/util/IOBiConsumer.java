package me.kazury.enkanetworkapi.genshin.util;

import java.io.IOException;

public interface IOBiConsumer<T, R> {
    void accept(T t, R r) throws IOException;
}
