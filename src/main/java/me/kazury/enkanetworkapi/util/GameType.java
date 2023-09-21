package me.kazury.enkanetworkapi.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * TODO - Add documentation
 */
@AllArgsConstructor
@Getter
public enum GameType {
    GENSHIN("https://gitlab.com/Dimbreath/AnimeGameData/-/raw/master/%s/%s"),
    HONKAI("https://raw.githubusercontent.com/Dimbreath/StarRailData/aa811519a5de772bf4055e8ea8b9254f90b7746c/%s/%s");

    @NotNull
    private final String url;
}
