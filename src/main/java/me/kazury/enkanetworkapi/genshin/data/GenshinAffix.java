package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * An affix of an artifact sub stat.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinAffix {
    private String propType;
    private double efficiency;
    private int position;

    public GenshinAffix() {}

    /**
     * The prop type, this is the name of the affix.
     * @see GenshinFightProp
     */
    public String getPropType() {
        return this.propType;
    }

    /**
     * The efficiency value of this affix.
     * <br>This is a value between 0.7 (70%) and 1 (100%).
     */
    public double getEfficiency() {
        return this.efficiency;
    }

    /**
     * The position of this affix.
     */
    public int getPosition() {
        return this.position;
    }
}
