package me.kazury.enkanetworkapi.util;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds data and its expiration time
 * @param <T> the data type which holds an instance of ttl time
 */
public class CachedData<T extends IExpiryTime> {
    @NotNull
    private final T data;

    private final long expirationTime;

    public CachedData(@NotNull T data) {
        this.data = data;
        this.expirationTime = System.currentTimeMillis() + (data.getTtl() * 1000L);
    }

    @NotNull
    public T getData() {
        return this.data;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= expirationTime;
    }
}
