package me.kazury.enkanetworkapi.genshin.data;

import org.jetbrains.annotations.NotNull;

public enum GenshinLocalization {
    TRADITIONAL_CHINESE("CHT"),
    SIMPLIFIED_CHINESE("CHS"),
    GERMAN("DE"),
    ENGLISH("EN"),
    ESPANOL("ES"),
    FRANCAIS("FR"),
    INDONESIAN("ID"),
    ITALIAN("IT"),
    JAPANESE("JP"),
    KOREAN("KR"),
    PORTUGUESE("PT"),
    RUSSIAN("RU"),
    THAI("TH"),
    TURKISH("TR"),
    VIETNAMESE("VI");

    private final String code;

    GenshinLocalization(@NotNull String code) {
        this.code = code;
    }

    @NotNull
    public String getCode() {
        return this.code;
    }
}
