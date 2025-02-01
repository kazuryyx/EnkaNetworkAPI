package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.util.GlobalLocalization;
import me.kazury.enkanetworkapi.games.genshin.util.GenshinNameable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder which is used for creating an {@link EnkaNetworkAPI} instance.
 *
 * <br><br><b>Example</b>:
 * <pre>{@code
 * final EnkaNetworkAPI api = new EnkaNetworkBuilder()
 *                 .setDefaultLocalization(GenshinLocalization.GERMAN)
 *                 .setUserAgent("My User Agent")
 *                 .build();
 * }</pre>
 */
public class EnkaNetworkBuilder {
    private GlobalLocalization defaultLocalization;
    private String baseUrl;
    private String userAgent;
    private boolean honkaiEnabled = false;
    private List<EnkaCache> blockedCaches = new ArrayList<>();

    /**
     * Sets the default localization which will be used for {@link GenshinNameable} objects.
     * @param localization The "new" default localization. Leaving this empty will be english.
     */
    @NotNull
    public EnkaNetworkBuilder setDefaultLocalization(@NotNull GlobalLocalization localization) {
        this.defaultLocalization = localization;
        return this;
    }

    /**
     * Sets the base URl for the API.
     * <br>This must have the full path, with https.
     * @param baseUrl The new base URL (which is used to grab images).
     */
    @NotNull
    public EnkaNetworkBuilder setBaseUrl(@NotNull String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Sets the user agent which will be used for HTTP requests.
     * @param agent The new user agent, prefer this to be your package name, or something else which identifies you.
     */
    @NotNull
    public EnkaNetworkBuilder setUserAgent(@NotNull String agent) {
        this.userAgent = agent;
        return this;
    }

    /**
     * Sets the status of Honkai: Star Rail.
     * <br>This is default false, but if you try to do anything related to Honkai: Star Rail you will receive an error.
     * <br>So if you want to use Honkai: Star Rail, you must enable it.
     */
    @NotNull
    public EnkaNetworkBuilder setHonkaiEnabled(final boolean status) {
        this.honkaiEnabled = status;
        return this;
    }

    /**
     * Sets the blocked caches.
     * @param caches The blocked caches.
     */
    @NotNull
    public EnkaNetworkBuilder setBlockedCaches(@NotNull EnkaCache... caches) {
        this.blockedCaches = List.of(caches);
        return this;
    }

    /**
     * Builds the {@link EnkaNetworkAPI} instance.
     * @return The {@link EnkaNetworkAPI} instance.
     */
    @NotNull
    public EnkaNetworkAPI build() {
        final EnkaNetworkAPI api = new EnkaNetworkAPI();
        api.enableHonkai(this.honkaiEnabled);

        if (this.defaultLocalization != null) {
            api.setDefaultLocalization(this.defaultLocalization);
        }

        if (this.baseUrl != null) {
            api.setDefaultUIPath(this.baseUrl);
        }

        if (this.userAgent != null) {
            api.setUserAgent(this.userAgent);
        }

        if (!this.blockedCaches.isEmpty()) {
            api.setBlockedCaches(this.blockedCaches);
        }
        return api.build();
    }
}
