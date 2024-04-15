package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that holds weaponId -> promoteId.
 * <br>This is mainly used for calculating the amount of materials needed for ascension.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinWeaponConfig {
    @JsonProperty("weaponPromoteId")
    private int weaponPromoteId;

    @JsonProperty("id")
    private int weaponId;

    public GenshinWeaponConfig() {}

    /**
     * Gets the promote id.
     */
    public int getWeaponPromoteId() {
        return this.weaponPromoteId;
    }

    /**
     * Gets the character id.
     */
    public int getWeaponId() {
        return this.weaponId;
    }
}
