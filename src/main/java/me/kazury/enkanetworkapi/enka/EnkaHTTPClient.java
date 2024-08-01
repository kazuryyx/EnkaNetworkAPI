package me.kazury.enkanetworkapi.enka;

import com.google.gson.Gson;
import me.kazury.enkanetworkapi.enka.page.EnkaProfileData;
import me.kazury.enkanetworkapi.genshin.data.conversion.GenshinUnconvertedUser;
import me.kazury.enkanetworkapi.util.exceptions.NiceJobException;
import me.kazury.enkanetworkapi.util.exceptions.PlayerDoesNotExistException;
import me.kazury.enkanetworkapi.util.exceptions.RateLimitedException;
import me.kazury.enkanetworkapi.util.exceptions.WrongUIDFormatException;
import me.kazury.enkanetworkapi.util.CachedData;
import me.kazury.enkanetworkapi.util.FunctionalCallback;
import me.kazury.enkanetworkapi.starrail.data.conversion.SRUnconvertedUser;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * The HTTP client for making requests to enka.network
 */
public class EnkaHTTPClient {
    private final String BASE_URL = "https://enka.network/api/";

    private final EnkaNetworkAPI api;
    private final Gson gson;

    public EnkaHTTPClient(@NotNull EnkaNetworkAPI api, @NotNull Gson gson) {
        this.api = api;
        this.gson = gson;
    }

    private String userAgent = "[EnkaNetworkAPI] Java - Unset User Agent";

    private final Map<Long, CachedData<GenshinUnconvertedUser>> genshinCache = new ConcurrentHashMap<>();
    private final Map<Long, CachedData<SRUnconvertedUser>> honkaiCache = new ConcurrentHashMap<>();

    protected void fetchGenshinUserFailure(final long uid, @NotNull Consumer<GenshinUnconvertedUser> success,
                                        @Nullable Consumer<Throwable> failure) {
        this.getBase("uid/" + uid + "/", GenshinUnconvertedUser.class).thenAccept((userData) -> {
            if (userData == null) return;
            this.genshinCache.put(uid, new CachedData<>(userData));
            success.accept(userData);
        }).exceptionally((exception) -> {
            if (failure != null) failure.accept(exception);
            return null;
        });
    }

    public void fetchGenshinUser(final long uid, @NotNull Consumer<GenshinUnconvertedUser> success) {
        final CachedData<GenshinUnconvertedUser> cachedData = this.genshinCache.get(uid);
        if (cachedData != null && cachedData.isValid()) {
            success.accept(cachedData.getData());
            return;
        }

        this.fetchGenshinUserFailure(uid, success, null);
    }

    public void fetchGenshinUser(final long uid, @NotNull Consumer<GenshinUnconvertedUser> success,
                                 @NotNull Consumer<Throwable> failure) {
        final CachedData<GenshinUnconvertedUser> cachedData = this.genshinCache.get(uid);
        if (cachedData != null && cachedData.isValid()) {
            success.accept(cachedData.getData());
            return;
        }

        this.fetchGenshinUserFailure(uid, success, failure);
    }

    protected void fetchHonkaiUserFailure(final long uid, @NotNull Consumer<SRUnconvertedUser> success,
                                 @Nullable Consumer<Throwable> failure) {
        EnkaVerifier.verifyHonkai();

        this.getBase("hsr/uid/" + uid + "/", SRUnconvertedUser.class).thenAccept((userData) -> {
            if (userData == null) return;
            this.honkaiCache.put(uid, new CachedData<>(userData));
            success.accept(userData);
        }).exceptionally((exception) -> {
            if (failure != null) failure.accept(exception);
            return null;
        });
    }

    public void fetchHonkaiUser(final long uid, @NotNull Consumer<SRUnconvertedUser> success) {
        final CachedData<SRUnconvertedUser> cachedData = this.honkaiCache.get(uid);
        if (cachedData != null && cachedData.isValid()) {
            success.accept(cachedData.getData());
            return;
        }

        this.fetchHonkaiUserFailure(uid, success, null);
    }

    public void fetchHonkaiUser(final long uid, @NotNull Consumer<SRUnconvertedUser> success,
                                @NotNull Consumer<Throwable> failure) {
        final CachedData<SRUnconvertedUser> cachedData = this.honkaiCache.get(uid);
        if (cachedData != null && cachedData.isValid()) {
            success.accept(cachedData.getData());
            return;
        }

        this.fetchHonkaiUserFailure(uid, success, failure);
    }

    protected void fetchProfileData(@NotNull String profileName, @NotNull Consumer<EnkaProfileData> success) {
        this.getBase("profile/" + profileName + "/?format=json", EnkaProfileData.class).thenAccept((userData) -> {
            if (userData == null) return;
            success.accept(userData);
        }).exceptionally((exception) -> null);
    }

    @NotNull
    private <K> CompletableFuture<K> getBase(@NotNull String endpoint, @NotNull Class<K> clazz) {
        final CompletableFuture<K> future = new CompletableFuture<>();
        final Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .addHeader("User-Agent", this.userAgent)
                .build();

        EnkaCaches.getClient().newCall(request).enqueue(FunctionalCallback.builder()
                .failure(($, exception) -> future.completeExceptionally(exception))
                .success(($, response) -> {
                    if (!response.isSuccessful()) {
                        switch (response.code()) {
                            case 400 -> future.completeExceptionally(new WrongUIDFormatException());
                            case 404 -> future.completeExceptionally(new PlayerDoesNotExistException());
                            case 429 -> future.completeExceptionally(new RateLimitedException());
                            case 503 -> future.completeExceptionally(new NiceJobException());
                        }
                        return;
                    }

                    if (response.body() == null) {
                        future.complete(null);
                        return;
                    }
                    final K result = this.gson.fromJson(response.body().string(), clazz);
                    if (result == null) {
                        future.completeExceptionally(new IOException("Could not cast response to " + clazz.getSimpleName()));
                        return;
                    }
                    future.complete(clazz.cast(result));
                }).build());
        return future;
    }

    public void setUserAgent(@NotNull String userAgent) {
        this.userAgent = userAgent;
    }
}
