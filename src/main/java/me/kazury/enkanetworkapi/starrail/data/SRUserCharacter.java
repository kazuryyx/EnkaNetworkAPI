package me.kazury.enkanetworkapi.starrail.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * This class is different from {@link SRCharacterData}
 * <br>This class is used to get the character data from the user.
 * <br>While {@link SRCharacterData} is used to get the character data from the game.
 */
@Builder
@Getter
public class SRUserCharacter {
    /**
     * The id of the character.
     */
    private int avatarId;
    /**
     * Represents the eidolon of the character.
     * <br>In Genshin terms: Constellation.
     */
    private int eidolon;
    /**
     * Represents the current level of the character.
     * <br>This is a range from 0 to 80.
     */
    private int level;
    /**
     * Represents the current ascension of the character.
     */
    private int ascension;
    /**
     * The weapon that this character currently has equipped.
     * <br>If the character has no weapon equipped, then this will be null.
     * @see SRLightcone
     */
    private SRLightcone lightcone;
    /**
     * Represents a relic that the character currently has equipped
     * <br>A character can have up to 6 relics equipped, including 4 sub stats on each.
     * @see SRRelic
     * @see SRRelicType
     */
    private List<SRRelic> relics;
}
