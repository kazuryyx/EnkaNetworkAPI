package me.kazury.enkanetworkapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * A game type, which is used to retrieve json data by url.
 */
@AllArgsConstructor
@Getter
public enum GameType {
    GENSHIN("https://gitlab.com/Dimbreath/AnimeGameData/-/raw/master/%s/%s"),
    HONKAI("https://raw.githubusercontent.com/Dimbreath/StarRailData/6acdba35cbf80adc100dbde528b1c271f50dcb9d/%s/%s");

    @NotNull
    private final String url;
}
