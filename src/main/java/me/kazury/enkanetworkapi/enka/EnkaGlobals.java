package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.genshin.data.GenshinLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnkaGlobals {
    private static GenshinLocalization DEFAULT_LOCALIZATION = GenshinLocalization.ENGLISH;

    public static void setDefaultLocalization(@NotNull GenshinLocalization localization) {
        DEFAULT_LOCALIZATION = localization;
    }

    @NotNull
    public static GenshinLocalization getDefaultLocalization() {
        return DEFAULT_LOCALIZATION;
    }

    @NotNull
    public static GenshinLocalization parseLocalization(@Nullable GenshinLocalization localization) {
        return localization == null ? getDefaultLocalization(): localization;
    }
}
