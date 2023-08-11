package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.genshin.util.INameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * An artifact that is currently equipped on a {@link GenshinUserCharacter}.
 */
@Builder
@Getter
public class GenshinArtifact implements INameable {
    /**
     * Represents the type of this artifact, this is either a flower, feather, sands, goblet, or a circlet.
     *
     * @see GenshinArtifactType
     */
    private final GenshinArtifactType type;
    /**
     * The level of the artifact, from 0 to 20.
     */
    private final int level;

    /**
     * The main stat of this artifact
     */
    private ArtifactStat mainStats;

    /**
     * All the substats of this artifact, there are 4 substats on one artifact
     */
    private List<ArtifactStat> subStats;

    /**
     * Represents a localization key for the artifact set name.
     */
    private String setNameTextMapHash;

    /**
     * The icon identifier of this artifact (flower icon, sands icon, etc.)
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getIcon(String)}
     */
    private String icon;

    @Override
    @NotNull
    public String getNameTextMapHash() {
        return this.setNameTextMapHash;
    }

    /**
     * A stat on an artifact, this is either a main stat or a substat.
     */
    @Getter
    @AllArgsConstructor
    public static class ArtifactStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
    }
}
