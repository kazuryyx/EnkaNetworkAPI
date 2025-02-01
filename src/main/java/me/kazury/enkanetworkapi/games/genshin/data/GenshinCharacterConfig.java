package me.kazury.enkanetworkapi.games.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that holds avatarId -> promoteId.
 * <br>This is mainly used for calculating the amount of materials needed for ascension.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinCharacterConfig {
    @JsonProperty("avatarPromoteId")
    private int avatarPromoteId;

    @JsonProperty("id")
    private int characterId;

    public GenshinCharacterConfig() {}

    /**
     * Gets the promote id.
     */
    public int getAvatarPromoteId() {
        return this.avatarPromoteId;
    }

    /**
     * Gets the character id.
     */
    public int getCharacterId() {
        return this.characterId;
    }
}
