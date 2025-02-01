package me.kazury.enkanetworkapi.games.genshin.data;

import org.jetbrains.annotations.NotNull;

/**
 * A namecard that is currently for show on a {@link GenshinUserInformation}.
 */
public class GenshinNamecard {
    private final int rawId;

    @NotNull
    private final String namecardUrl;

    public GenshinNamecard(final int rawId, @NotNull String namecardUrl) {
        this.rawId = rawId;
        this.namecardUrl = namecardUrl;
    }

    /**
     * @return The raw ID of the namecard.
     */
    public int getRawId() {
        return this.rawId;
    }

    /**
     * A namecard URL that can be used to display the namecard.
     */
    @NotNull
    public String getNamecardUrl() {
        return this.namecardUrl;
    }
}
