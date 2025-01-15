package me.kazury.enkanetworkapi.starrail.data;

import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;

public enum SRCharacterPath {
    PRESERVATION("KNIGHT"),
    ERUDITION("MAGE"),
    ABUNDANCE("PRIEST"),
    THE_HUNT("ROGUE"),
    HARMONY("SHAMAN"),
    NIHILITY("WARLOCK"),
    DESTRUCTION("WARRIOR"),
    REMEMBRANCE("MEMORY");

    private final String internalId;

    SRCharacterPath(@NotNull String internalId) {
        this.internalId = internalId;
    }

    @NotNull
    public String getInternalId() {
        return this.internalId;
    }

    @NotNull
    public static SRCharacterPath fromInternalData(@NotNull String path) {
        for (SRCharacterPath value : values()) {
            if (value.getInternalId().equalsIgnoreCase(path)) {
                return value;
            }
        }
        throw new UpdateLibraryException();
    }
}
