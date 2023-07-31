package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.genshin.data.GenshinLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnkaGlobals {
    private static GenshinLocalization DEFAULT_LOCALIZATION;

    public static void setDefaultLocalization(@NotNull GenshinLocalization localization) {
        DEFAULT_LOCALIZATION = localization;
    }

    @Nullable
    public static GenshinLocalization getDefaultLocalization() {
        return DEFAULT_LOCALIZATION;
    }

    @Nullable
    public static GenshinLocalization parseLocalization(@Nullable GenshinLocalization localization) {
        return localization == null ? getDefaultLocalization() : localization;
    }
}
