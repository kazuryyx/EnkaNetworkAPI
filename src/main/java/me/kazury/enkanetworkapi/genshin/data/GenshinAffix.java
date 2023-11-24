package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinAffix {
    /**
     * TODO - Add documentation
     */
    private String propType;
    /**
     * TODO - Add documentation
     */
    private double efficiency;
    /**
     * TODO - Add documentation
     */
    private int position;

    public GenshinAffix() {}
}
