package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class GenshinProfilePicture {
    /**
     * The id of this profile picture
     */
    private final int id;
    /**
     * The circle icon path for this profile picture
     */
    private final String iconPath;
    /**
     * How to obtain this profile picture:
     * <ul>
     *     <li>PROFILE_PICTURE_UNLOCK_BY_ITEM</li>
     *     <li>PROFILE_PICTURE_UNLOCK_BY_COSTUME</li>
     * </ul>
     */
    private final String unlockType;
    /**
     * I don't know what this is, internal field: "CPBELMNGNEK"
     */
    private final long internalId;

    public GenshinProfilePicture(@NotNull JsonNode profile) {
        this.id = profile.get("id").asInt();
        this.iconPath = profile.get("iconPath").asText();
        this.unlockType = profile.get("KJEOGPCNAOJ").asText();
        this.internalId = profile.get("CPBELMNGNEK").asLong();
    }
}
