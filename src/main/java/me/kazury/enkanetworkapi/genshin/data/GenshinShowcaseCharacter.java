package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * A character that is currently in a showcase.
 * <br>You may not need this class, as the level and costumes are supplied on {@link GenshinUserCharacter}
 */
@Getter
@AllArgsConstructor
@Builder
public class GenshinShowcaseCharacter {
    /**
     * The ID of the character.
     */
    private final int avatarId;
    /**
     * The level of the character. Range from 0 to 90.
     */
    private final int level;
    /**
     * The ID of the costume that the character is wearing.
     * Check <a href="https://github.com/EnkaNetwork/API-docs/blob/master/store/characters.json">Costumes</a> for more information.
     */
    private final int costumeId;
}
