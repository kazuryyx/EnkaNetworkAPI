package me.kazury.enkanetworkapi.starrail.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import me.kazury.enkanetworkapi.enka.EnkaCache;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import org.jetbrains.annotations.NotNull;

/**
 * Lightcone data for Honkai: Star Rail.
 * <br>If {@link EnkaCache#HONKAI_LIGHTCONES} is disabled, this will not load.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SRLightconeData {
    @JsonProperty("Rarity")
    private int stars;

    @JsonProperty("AvatarBaseType")
    private String path;

    @JsonProperty("ImagePath")
    private String imagePath;

    /**
     * The amount of stars this lightcone has.
     */
    public int getStars() {
        return this.stars;
    }

    /**
     * The path that this lightcone belongs to.
     * <br>Players can equip lightcones from different paths, however the boost / passive will only work if the character path is equal to this.
     */
    @NotNull
    public SRCharacterPath getPath() {
        return SRCharacterPath.fromInternalData(this.path);
    }

    /**
     * Gets the imagepath of this lightcone.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getSRIcon(String)}
     */
    @NotNull
    public String getImagePath() {
        return this.imagePath;
    }
}
