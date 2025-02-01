package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.games.genshin.data.GenshinArtifactType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A parser which is used for parsing json data to enums
 */
public class EnkaParser {
    /**
     * Parses a json identifier to an artifact type enum
     * @param identifier the json identifier
     * @return the artifact type enum
     */
    @Nullable
    public static GenshinArtifactType parseArtifact(@NotNull String identifier) {
        return switch (identifier) {
            case "EQUIP_BRACER" -> GenshinArtifactType.FLOWER;
            case "EQUIP_NECKLACE" -> GenshinArtifactType.FEATHER;
            case "EQUIP_SHOES" -> GenshinArtifactType.SANDS;
            case "EQUIP_RING" -> GenshinArtifactType.GOBLET;
            case "EQUIP_DRESS" -> GenshinArtifactType.CIRCLET;
            default -> null;
        };
    }
}
