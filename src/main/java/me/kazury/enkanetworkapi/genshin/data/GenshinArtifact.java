package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.exceptions.NoLocalizationFoundException;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Builder
@Getter
public class GenshinArtifact {
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

    /**
     * Returns the name of this artifact given by the specified locale.
     * @param locale The locale to get the name from. If {@code null}, then the default locale will be used.
     * @return The name of the artifact.
     * @throws NoLocalizationFoundException If the specified locale is {@code null} and the default locale is {@code null}.
     */
    @Nullable
    public String getArtifactName(@Nullable GenshinLocalization locale) {
        locale = EnkaGlobals.parseLocalization(locale);
        return EnkaCaches.getLocale(locale, this.setNameTextMapHash);
    }

    /**
     * Returns the name of this artifact given by the default locale.
     *
     * @return The name of the artifact with the default locale.
     * @throws NoLocalizationFoundException If the default locale is {@code null}.
     */
    public String getArtifactName() {
        return this.getArtifactName(null);
    }

    @Getter
    @AllArgsConstructor
    public static class ArtifactStat {
        private final String stat;
        private final double value;
    }
}
