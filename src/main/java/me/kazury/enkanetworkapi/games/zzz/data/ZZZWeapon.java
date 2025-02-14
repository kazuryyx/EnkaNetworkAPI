package me.kazury.enkanetworkapi.games.zzz.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.games.zzz.util.ZZZNameable;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a weapon taken from the JSON data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZZZWeapon implements ZZZNameable {
    @JsonProperty("ItemName")
    private String itemNameHash;

    @JsonProperty("Rarity")
    private int rarity;

    @JsonProperty("ProfessionType")
    private String professionType;

    @JsonProperty("ImagePath")
    private String imagePath;

    @JsonProperty("MainStat")
    private ZZZStatProperty mainStat;

    @JsonProperty("SecondaryStat")
    private ZZZStatProperty secondaryStat;

    /**
     * Returns the rarity of this weapon.
     * <br>Ranges from 2 to 4.
     */
    public int getRarity() {
        return this.rarity;
    }

    /**
     * Returns the profession type of this weapon.
     * <ul>
     *     <li>Attack</li>
     *     <li>Support</li>
     *     <li>Stun</li>
     *     <li>Anomaly</li>
     *     <li>Defense</li>
     * </ul>
     */
    @NotNull
    public String getProfessionType() {
        return this.professionType;
    }

    /**
     * Gets the image path of this weapon.
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getZenlessIcon(String)}.
     */
    @NotNull
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Returns the main stat information of this type.
     * <br>This can be used to get the main stat of the weapon.
     */
    @NotNull
    public ZZZStatProperty getMainStat() {
        return this.mainStat;
    }

    /**
     * Returns the secondary stat information of this type.
     * <br>This can be used to get the secondary stat of the weapon.
     */
    @NotNull
    public ZZZStatProperty getSecondaryStat() {
        return this.secondaryStat;
    }

    @Override
    @NotNull
    public String getNameHash() {
        return itemNameHash;
    }
}
