package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.genshin.data.GenshinArtifactType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnkaParser {
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
