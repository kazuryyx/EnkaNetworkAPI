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
    HONKAI("https://raw.githubusercontent.com/Dimbreath/StarRailData/59d64be43a1da285cf22ba9be5ed90ef2b23f857/%s/%s");

    @NotNull
    private final String url;
}
