package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

public class GenshinProfilePicture {
    private final int id;

    @NotNull
    private final String iconPath;

    @NotNull
    private final String unlockType;

    private final long internalId;

    public GenshinProfilePicture(@NotNull JsonNode profile) {
        this.id = profile.get("id").asInt();
        this.iconPath = profile.get("iconPath").asText();
        this.unlockType = profile.get("KJEOGPCNAOJ").asText();
        this.internalId = profile.get("CPBELMNGNEK").asLong();
    }

    /**
     * The id of this profile picture
     */
    public int getId() {
        return this.id;
    }

    /**
     * The circle icon path for this profile picture
     */
    @NotNull
    public String getIconPath() {
        return this.iconPath;
    }

    /**
     * How to obtain this profile picture:
     * <ul>
     *     <li>PROFILE_PICTURE_UNLOCK_BY_ITEM</li>
     *     <li>PROFILE_PICTURE_UNLOCK_BY_COSTUME</li>
     * </ul>
     */
    @NotNull
    public String getUnlockType() {
        return this.unlockType;
    }

    /**
     * I don't know what this is, internal field: "CPBELMNGNEK"
     */
    public long getInternalId() {
        return this.internalId;
    }
}
