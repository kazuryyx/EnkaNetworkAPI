package me.kazury.enkanetworkapi.games.zzz.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a property for a stat.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZZZStatProperty {
    @JsonProperty("PropertyId")
    private int propertyId;

    @JsonProperty("PropertyValue")
    private int propertyValue;

    /**
     * Returns the property id of this type (Mainstat or Substat).
     */
    public int getPropertyId() {
        return this.propertyId;
    }

    /**
     * Returns the property value of this type.
     */
    public int getPropertyValue() {
        return this.propertyValue;
    }
}
