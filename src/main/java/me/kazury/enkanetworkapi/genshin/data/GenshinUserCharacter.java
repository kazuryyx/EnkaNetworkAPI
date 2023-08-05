package me.kazury.enkanetworkapi.genshin.data;

import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is different from {@link GenshinCharacterData}
 * <br>This class is used to get the character data from the user.
 * <br>While {@link GenshinCharacterData} is used to get the character data from the game.
 */
@Builder
@Getter
public class GenshinUserCharacter {
    /**
     * The id of the character.
     */
    private final int id;
    /**
     * Represents the constellation level of the character.
     */
    private final int constellation;

    /**
     * Represents a map of the character's properties.
     * <br>For example, the character's level, health, attack, etc.
     * @see GenshinFightProp
     */
    private Map<GenshinFightProp, Double> fightProperties = new HashMap<>();

    /**
     * Represents an artifact that the character currently has equipped
     * <br>A character can have up to 5 artifacts equipped, including 4 sub stats on each.
     * @see GenshinArtifact
     * @see GenshinArtifactType
     */
    private List<GenshinArtifact> artifacts = new ArrayList<>();

    /**
     * The weapon that this character currently has equipped.
     * @see GenshinUserWeapon
     */
    private GenshinUserWeapon equippedWeapon;

    /**
     * @see GenshinCharacterData
     * @throws NullPointerException if the library version is not the version of genshin.
     * @return The game data of this character, you can use this to get costume info, character name, and much more that this class does not provide.
     */
    @NotNull
    public GenshinCharacterData getGameData() {
        final GenshinCharacterData data = EnkaCaches.getCharacterData(String.valueOf(this.id));
        if (data == null) {
            throw new NullPointerException("Character data is null - This usually does not happen, unless the library is on an old version.");
        }
        return data;
    }
}
