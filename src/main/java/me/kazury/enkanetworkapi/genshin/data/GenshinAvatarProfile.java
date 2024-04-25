package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

/**
 * A profile which is used for avatars.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinAvatarProfile {
    @JsonProperty("id")
    private long id;

    @JsonProperty("iconPath")
    private String image;

    /**
     * Gets the id of this object
     * <br>This is either the character id or 0
     * @return the id
     */
    public long getId() {
        return this.id;
    }

    /**
     * Gets the identifier for this profile
     * @return the image path
     */
    @NotNull
    public String getImage() {
        return this.image;
    }
}
