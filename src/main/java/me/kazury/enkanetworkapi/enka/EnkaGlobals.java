package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.util.GlobalLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnkaGlobals {
    private static GlobalLocalization DEFAULT_LOCALIZATION = GlobalLocalization.ENGLISH;

    /**
     * Sets the default localization
     * @param localization the "new" localization
     */
    public static void setDefaultLocalization(@NotNull GlobalLocalization localization) {
        DEFAULT_LOCALIZATION = localization;
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
     * Parses a localization, if the localization is null, it will return the default localization
     * @param localization the localization
     * @return the parsed localization or the default localization
     */
    @NotNull
    public static GlobalLocalization parseLocalization(@Nullable GlobalLocalization localization) {
        return localization == null ? getDefaultLocalization(): localization;
    }
}
