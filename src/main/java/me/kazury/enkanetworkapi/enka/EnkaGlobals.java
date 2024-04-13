package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.util.GlobalLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnkaGlobals {
    private static GlobalLocalization DEFAULT_LOCALIZATION = GlobalLocalization.ENGLISH;
    private static final List<EnkaCache> BLOCKED_CACHES = new ArrayList<>();
    private static boolean HONKAI_ENABLED = false;

    /**
     * Sets the default localization
     * @param localization the "new" localization
     */
    public static void setDefaultLocalization(@NotNull GlobalLocalization localization) {
        DEFAULT_LOCALIZATION = localization;
    }

    /**
     * Sets the status of Honkai: Star Rail.
     * @param status the new status
     */
    public static void setHonkaiEnabled(final boolean status) {
        HONKAI_ENABLED = status;
    }

    /**
     * @return the blocked caches
     */
    public static void setBlockedCaches(@NotNull List<EnkaCache> caches) {
        BLOCKED_CACHES.clear();
        BLOCKED_CACHES.addAll(caches);
        EnkaCaches.loadCaches();
    }

    /**
     * Checks if a cache is blocked
     */
    public static boolean isCacheBlocked(@NotNull EnkaCache cache) {
        return BLOCKED_CACHES.contains(cache);
    }

    /**
     * @return the default localization
     */
    @NotNull
    public static GlobalLocalization getDefaultLocalization() {
        EnkaCaches.loadLocalizations(DEFAULT_LOCALIZATION);
        return DEFAULT_LOCALIZATION;
    }

    /**
     * @return if Honkai: Star Rail is enabled
     */
    public static boolean isHonkaiEnabled() {
        return HONKAI_ENABLED;
    }

    /**
     * Parses a localization, if the localization is null, it will return the default localization
     * @param localization the localization
     * @return the parsed localization or the default localization
     */
    @NotNull
    public static GlobalLocalization parseLocalization(@Nullable GlobalLocalization localization) {
        return localization == null ? getDefaultLocalization(): localization;
    }
}
