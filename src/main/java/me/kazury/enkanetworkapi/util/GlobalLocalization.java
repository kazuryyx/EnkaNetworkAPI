package me.kazury.enkanetworkapi.util;

import org.jetbrains.annotations.NotNull;

/**
 * A localization that you can choose from
 */
public enum GlobalLocalization {
    TRADITIONAL_CHINESE("CHT"),
    SIMPLIFIED_CHINESE("CHS"),
    GERMAN("DE"),
    ENGLISH("EN"),
    ESPANOL("ES"),
    FRANCAIS("FR"),
    INDONESIAN("ID"),
    /**
     * Do not use for Honkai: Star Rail
     */
    ITALIAN("IT"),
    JAPANESE("JP"),
    KOREAN("KR"),
    PORTUGUESE("PT"),
    RUSSIAN("RU"),
    THAI("TH"),
    /**
     * Do not use for Honkai: Star Rail
     */
    TURKISH("TR"),
    VIETNAMESE("VI");

    private final String code;

    GlobalLocalization(@NotNull String code) {
        this.code = code;
    }

    @NotNull
    public String getCode() {
        return this.code;
    }
}
