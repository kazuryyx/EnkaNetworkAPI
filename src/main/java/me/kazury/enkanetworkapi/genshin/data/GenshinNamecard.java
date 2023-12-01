package me.kazury.enkanetworkapi.genshin.data;

import org.jetbrains.annotations.Nullable;

/**
 * A namecard that is currently for show on a {@link GenshinUserInformation}.
 */
public class GenshinNamecard {
    private final int rawId;

    @Nullable
    private final String namecardUrl;

    public GenshinNamecard(final int rawId,
                           @Nullable String namecardUrl) {
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
    @Nullable
    public String getNamecardUrl() {
        return this.namecardUrl;
    }
}
