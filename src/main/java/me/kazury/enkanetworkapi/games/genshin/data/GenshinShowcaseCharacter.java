package me.kazury.enkanetworkapi.games.genshin.data;

/**
 * A character that is currently in a showcase.
 * <br>You may not need this class, as the level and costumes are supplied on {@link GenshinUserCharacter}
 */
public class GenshinShowcaseCharacter {
    private final int avatarId;
    private final int level;

    private final int costumeId;

    public GenshinShowcaseCharacter(final int avatarId, final int level, final int costumeId) {
        this.avatarId = avatarId;
        this.level = level;
        this.costumeId = costumeId;
    }

    /**
     * The ID of the character.
     */
    public int getAvatarId() {
        return this.avatarId;
    }
    /**
     * The level of the character. Range from 0 to 90.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The ID of the costume that the character is wearing.
     * Check <a href="https://github.com/EnkaNetwork/API-docs/blob/master/store/characters.json">Costumes</a> for more information.
     */
    public int getCostumeId() {
        return this.costumeId;
    }
}
