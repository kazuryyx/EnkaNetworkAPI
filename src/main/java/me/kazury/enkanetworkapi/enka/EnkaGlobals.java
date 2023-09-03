package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.genshin.data.GenshinLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnkaGlobals {
    private static GenshinLocalization DEFAULT_LOCALIZATION = GenshinLocalization.ENGLISH;

    /**
     * Sets the default localization
     * @param localization the "new" localization
     */
    public static void setDefaultLocalization(@NotNull GenshinLocalization localization) {
        DEFAULT_LOCALIZATION = localization;
    }

    /**
     * @return the default localization
     */
    @NotNull
    public static GenshinLocalization getDefaultLocalization() {
        EnkaCaches.loadLocalization(DEFAULT_LOCALIZATION);
        return DEFAULT_LOCALIZATION;
    }

    /**
     * Parses a localization, if the localization is null, it will return the default localization
     * @param localization the localization
     * @return the parsed localization or the default localization
     */
    @NotNull
    public static GenshinLocalization parseLocalization(@Nullable GenshinLocalization localization) {
        return localization == null ? getDefaultLocalization(): localization;
    }
}
