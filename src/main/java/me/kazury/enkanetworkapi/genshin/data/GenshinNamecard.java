package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Getter
public class GenshinNamecard {
    private final int rawId;

    /**
     * A namecard URL that can be used to display the namecard.
     */
    @Nullable
    private final String namecardUrl;
}
