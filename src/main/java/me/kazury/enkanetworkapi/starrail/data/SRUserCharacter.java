package me.kazury.enkanetworkapi.starrail.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * TODO - Add documentation
 */
@Builder
@Getter
public class SRUserCharacter {
    /**
     * TODO - Add documentation
     */
    private int avatarId;
    /**
     * TODO - Add documentation
     */
    private int eidolon;
    /**
     * TODO - Add documentation
     */
    private int level;
    /**
     * TODO - Add documentation
     */
    private int ascension;
    /**
     * TODO - Add documentation
     */
    private SRLightcone lightcone;
    /**
     * TODO - Add documentation
     */
    private List<SRRelic> relics;
}
