package me.kazury.enkanetworkapi.enka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.genshin.exceptions.*;
import me.kazury.enkanetworkapi.genshin.util.FunctionalCallback;
import me.kazury.enkanetworkapi.genshin.util.CachedData;
import me.kazury.enkanetworkapi.genshin.data.conversion.EnkaUserInformation;
import me.kazury.enkanetworkapi.genshin.data.GenshinUserInformation;

import me.kazury.enkanetworkapi.genshin.util.INameable;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class EnkaNetworkAPI {
    private static final String BASE_URL = "https://enka.network/api";
    public static final String BASE_UI_URL = "https://enka.network/ui/";

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final Map<Long, CachedData<EnkaUserInformation>> userCache;

    private String userAgent = "Java-EnkaAPI/1.0.0";

    public EnkaNetworkAPI() {
        this.httpClient = new OkHttpClient();
        this.gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        this.userCache = new ConcurrentHashMap<>();
    }

    /**
     * Loads a user from the Enka Network API and passes on the action that you want to do on the user.
     * @param uid The <a href="https://genshin-impact.fandom.com/wiki/UID">UID</a> of the user.
     * @param consumer The action that you want to do on the user once the data has been acquired.
     *                 This method is async and the request will be queued to avoid spamming the API.
     */
    public void fetchUser(final long uid, @NotNull Consumer<EnkaUserInformation> consumer) {
        final CachedData<EnkaUserInformation> cachedData = this.userCache.get(uid);
        if (cachedData != null && !cachedData.isExpired()) {
            consumer.accept(cachedData.getData());
            return;
        }

        this.getBase("/uid/" + uid, EnkaUserInformation.class).thenAccept((userData) -> {
            if (userData == null) return;
            this.userCache.put(uid, new CachedData<>(userData));
            consumer.accept(userData);
        }).exceptionally((exception) -> {
            exception.printStackTrace();
            return null;
        });
    }

    /**
     * Fetches the namecard with an id.
     * <br> Namecard IDs are provided where you need them:
     * <ul>
     *     <li>{@link GenshinUserInformation#getNamecardId()}</li>
     *     <li>{@link GenshinUserInformation#getNameCards()}</li>
     * </ul>
     * @param id The ID of the namecard.
     * @return The namecard, or null if the namecard does not exist (or I forgot to update my library)
     */
    @Nullable
    public GenshinNamecard getNamecard(final int id) {
        return EnkaCaches.hasNamecard(id) ? new GenshinNamecard(id, this.getIcon(EnkaCaches.getNamecardName(id))) : null;
    }

    /**
     * Fetches the character data of a user.
     * @param id The ID of the character. This is not the character's name.
     * @return The character data, or null if the character does not exist (or I forgot to update my library)
     */
    @Nullable
    public GenshinCharacterData getCharacterData(@NotNull String id) {
        return EnkaCaches.getCharacterData(id);
    }

    /**
     * Returns the total amount of set artifacts this character has.
     *
     * @param character The character to get the total amount of set artifacts from.
     * @return A map of artifacts this character has.
     */
    @NotNull
    public Map<String, Integer> getArtifactTotal(@NotNull GenshinUserCharacter character) {
        final Map<String, Integer> artifacts = new HashMap<>();
        for (GenshinArtifact artifact : character.getArtifacts()) {
            artifacts.put(artifact.getName(), artifacts.getOrDefault(artifact.getName(), 0) + 1);
        }
        return artifacts;
    }

    /**
     * Sets the default localization.
     * <br>You might notice, some methods with translation require you to pass in a translation, but you can also decide to not put any parameters,
     * and so the default translation will be used (which will be english).
     * @param localization The default localization.
     * @see INameable
     */
    public void setDefaultLocalization(@NotNull GenshinLocalization localization) {
        EnkaGlobals.setDefaultLocalization(localization);
    }

    /**
     * Sets the user agent for the HTTP requests when fetching {@link EnkaUserInformation}.
     * <br><b>Quote: </b><i>Please set a custom User-Agent header with your requests so I can track them better and help you if needed.</i>
     * @param userAgent The user agent.
     */
    public void setUserAgent(@NotNull String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Fetches the character data of a user.
     * @param id The ID of the character. This is not the character's name.
     * @return The character data, or null if the character does not exist (or I forgot to update my library)
     */
    @Nullable
    public GenshinCharacterData getCharacterData(final long id) {
        return this.getCharacterData(String.valueOf(id));
    }

    /**
     * Gets an icon by the icon id
     * @param key The icon id, it is usually an uppercase char sequence
     * @return The icon url or null if the icon does not exist
     */
    @Nullable
    public String getIcon(@NotNull String key) {
        return BASE_UI_URL + key + ".png";
    }

    @NotNull
    private <K> CompletableFuture<K> getBase(@NotNull String endpoint, @NotNull Class<K> clazz) {
        final CompletableFuture<K> future = new CompletableFuture<>();
        final Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .addHeader("User-Agent", this.userAgent)
                .build();

        this.httpClient.newCall(request).enqueue(FunctionalCallback.builder()
                .failure(($, exception) -> future.completeExceptionally(exception))
                .success(($, response) -> {
            if (!response.isSuccessful()) {
                if (clazz != EnkaUserInformation.class) {
                    future.completeExceptionally(new IOException("Could not fetch user data, code " + response.code()));
                    return;
                }

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
}