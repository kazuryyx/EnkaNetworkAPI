package me.kazury.enkanetworkapi.genshin.data;

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
public class GenshinMaterial {

    private final int id;

    @Nullable
    private final String name;

    @Nullable
    private final String description;

    @NotNull
    private final String icon;

    @NotNull
    private final List<String> pictures;

    @NotNull
    private final GenshinItemType itemType;

    @Nullable
    private String materialType;

    @Nullable
    private String stars;

    public GenshinMaterial(@NotNull JsonNode jsonNode) {
        this.id = jsonNode.get("id").asInt();
        this.name = EnkaCaches.getGenshinLocale(EnkaGlobals.getDefaultLocalization(), jsonNode.get("nameTextMapHash").asText());
        this.description = EnkaCaches.getGenshinLocale(EnkaGlobals.getDefaultLocalization(), jsonNode.get("descTextMapHash").asText());
        this.icon = jsonNode.get("icon").asText();
        this.pictures = jsonNode.findValuesAsText("picPath");
        this.itemType = GenshinItemType.valueOf(jsonNode.get("itemType").asText());

        if (jsonNode.has("materialType")) {
            this.materialType = jsonNode.get("materialType").asText();
        }

        if (jsonNode.has("rankLevel")) {
            this.stars = jsonNode.get("rankLevel").asText();
        }
    }

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
        return this.name;
    }

    /**
     * The description of this object
     */
    @Nullable
    public String getDescription() {
        return this.description;
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
     */
    @NotNull
    public GenshinItemType getItemType() {
        return this.itemType;
    }

    /**
     * The material type of this object
     * <br>Only available for {@link GenshinItemType#ITEM_MATERIAL}
     */
    @Nullable
    public String getMaterialType() {
        return this.materialType;
    }

    /**
     * The stars of this object
     * <br>Only available for {@link GenshinItemType#ITEM_MATERIAL}
     */
    @Nullable
    public String getStars() {
        return this.stars;
    }
}
