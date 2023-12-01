package me.kazury.enkanetworkapi.util;

import org.jetbrains.annotations.NotNull;

/**
 * A game type, which is used to retrieve json data by url.
 */
public enum GameType {
    GENSHIN("https://gitlab.com/Dimbreath/AnimeGameData/-/raw/master/%s/%s"),
    HONKAI("https://raw.githubusercontent.com/Dimbreath/StarRailData/59d64be43a1da285cf22ba9be5ed90ef2b23f857/%s/%s");

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
