package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import org.jetbrains.annotations.NotNull;

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
@Getter
public class GenshinMaterial {
    /**
     * The id of this material, this is what you used to get this object.
     */
    private final int id;
    /**
     * The name of this object.
     */
    private final String name;
    /**
     * The description of this object
     */
    private final String description;
    /**
     * The icon of this object, you will have to parse it yourself.
     */
    private final String icon;
    /**
     * The list of pictures this object has, or mostly empty
     */
    private final List<String> pictures;
    /**
     * The item type of this object
     */
    private final GenshinItemType itemType;

    /**
     * The material type of this object
     * <br>Only available for {@link GenshinItemType#ITEM_MATERIAL}
     */
    private String materialType;
    /**
     * The stars of this object
     * <br>Only available for {@link GenshinItemType#ITEM_MATERIAL}
     */
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

        if (jsonNode.has("rarity")) {
            this.stars = jsonNode.get("rankLevel").asText();
        }
    }
}
