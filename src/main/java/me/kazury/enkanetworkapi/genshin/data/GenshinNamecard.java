package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

/**
 * A namecard that is currently for show on a {@link GenshinUserInformation}.
 */
@AllArgsConstructor
@Getter
@Builder
public class GenshinNamecard {
    private final int rawId;

    /**
     * A namecard URL that can be used to display the namecard.
     */
    @Nullable
    private final String namecardUrl;
}
