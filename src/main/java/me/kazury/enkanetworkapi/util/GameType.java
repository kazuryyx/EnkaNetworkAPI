package me.kazury.enkanetworkapi.util;

import org.jetbrains.annotations.NotNull;

/**
 * A game type, which is used to retrieve json data by url.
 */
public enum GameType {
    GENSHIN("https://gitlab.com/Dimbreath/AnimeGameData/-/raw/master/%s/%s"),
    HONKAI("https://gitlab.com/Dimbreath/turnbasedgamedata/-/raw/main/%s/%s");

    @NotNull
    private final String url;

    GameType(@NotNull String url) {
        this.url = url;
    }

    /**
     * @return URL of the game type to fetch data.
     */
    @NotNull
    public String getUrl() {
        return this.url;
    }
}
