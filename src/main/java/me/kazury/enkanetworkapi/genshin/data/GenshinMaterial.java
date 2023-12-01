package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A material
 * <br>Possible options:
 * <ul>
 *     <li>Food</li>
 *     <li>Common Ascension Material</li>
 *     <li>Character Ascension Material</li>
 *     <li>Weapon Ascension Material</li>
 *     <li>Talent Level-Up Material</li>
 *     <li>Weapon Level-Up Material</li>
 *     <li>etc</li>
 * </ul>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinMaterial {
    private int id;

    @Nullable
    @JsonProperty("nameTextMapHash")
    private String name;

    @Nullable
    @JsonProperty("descTextMapHash")
    private String description;

    @NotNull
    @JsonProperty("icon")
    private String icon;

    @NotNull
    @JsonProperty("picPath")
    private List<String> pictures;

    @NotNull
    @JsonProperty("itemType")
    private String itemType;

    @JsonProperty("rankLevel")
    private int stars;

    public GenshinMaterial() {}

    /**
     * The id of this material, this is what you used to get this object.
     */
    public int getId() {
        return this.id;
    }

    /**
     * The name of this object.
     */
    @Nullable
    public String getName() {
        if (this.name == null) return null;
        return EnkaCaches.getGenshinLocale(EnkaGlobals.getDefaultLocalization(), this.name);
    }

    /**
     * The description of this object
     */
    @Nullable
    public String getDescription() {
        if (this.description == null) return null;
        return EnkaCaches.getGenshinLocale(EnkaGlobals.getDefaultLocalization(), this.description);
    }

    /**
     * The icon of this object, you will have to parse it yourself.
     */
    @NotNull
    public String getIcon() {
        return this.icon;
    }

    /**
     * The list of pictures this object has, or mostly empty
     */
    @NotNull
    public List<String> getPictures() {
        return this.pictures;
    }

    /**
     * The item type of this object
     * <ul>
     *     <li>ITEM_VIRTUAL</li>
     *     <li>ITEM_MATERIAL</li>
     *     <li>UNIMPLEMENTED</li>
     * </ul>
     */
    @NotNull
    public String getItemType() {
        return this.itemType;
    }

    /**
     * The stars of this object
     * <br>Only available for {@link GenshinItemType#ITEM_MATERIAL}
     */
    public int getStars() {
        return this.stars;
    }
}
