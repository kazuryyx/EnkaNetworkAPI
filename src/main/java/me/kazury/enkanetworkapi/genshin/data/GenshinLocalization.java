package me.kazury.enkanetworkapi.genshin.data;

import org.jetbrains.annotations.NotNull;

public enum GenshinLocalization {
    ENGLISH("en"),
    RUSSIAN("ru"),
    VIETNAMESE("vi"),
    THAI("th"),
    PORTUGUESE("pt"),
    KOREAN("ko"),
    JAPANESE("ja"),
    INDONESIAN("id"),
    FRANCAIS("fr"),
    ESPANOL("es"),
    GERMAN("de"),
    TRADITIONAL_CHINESE("zh-TW"),
    SIMPLIFIED_CHINESE("zh-CN"),
    ITALIAN("it"),
    TURKISH("tr");

    private final String code;

    GenshinLocalization(@NotNull String code) {
        this.code = code;
    }

    @NotNull
    public String getCode() {
        return this.code;
    }
}
