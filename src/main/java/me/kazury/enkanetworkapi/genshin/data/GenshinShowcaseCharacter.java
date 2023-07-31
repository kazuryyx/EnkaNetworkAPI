package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
