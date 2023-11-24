package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

/**
 * An affix of an artifact sub stat.
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinAffix {
    /**
     * The prop type, this is the name of the affix.
     * @see GenshinFightProp
     */
    private String propType;
    /**
     * The efficiency value of this affix.
     * <br>This is a value between 0.7 (70%) and 1 (100%).
     */
    private double efficiency;
    /**
     * The position of this affix.
     */
    private int position;

    public GenshinAffix() {}
}
