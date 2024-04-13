package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.genshin.data.GenshinUserInformation;
import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.genshin.data.GenshinArtifact;

/**
 * Represents a cache enum that can be used to block certain caches.
 * <br>This is not only to save memory, but also to prevent unnecessary requests to the excel data.
 */
public enum EnkaCache {
    /**
     * The namecard cache for genshin
     */
    GENSHIN_NAMECARDS,
    /**
     * The characters for genshin. It is not recommended that you block this because it contains various assets.
     * <br>However, if you don't need the assets, you can block this.
     */
    GENSHIN_CHARACTERS,
    /**
     * Genshin Profiles, if you don't need the profiles, you can block this.
     * <br>Mainly used for {@link EnkaNetworkAPI#getGenshinProfileIdentifier(Pair)} retrieved by {@link GenshinUserInformation#getProfilePictureId()}
     */
    GENSHIN_PROFILES,
    /**
     * Genshin Affixes, this is used if you want to get RV (Roll Value) from a specific artifact.
     * @see GenshinArtifact#getRollData(EnkaNetworkAPI)
     */
    GENSHIN_AFFIXES,
    /**
     * Genshin Materials, this is used for getting the materials needed for ascension.
     * <br>TODO add @see if implemented getAscensionData
     */
    GENSHIN_MATERIALS,
    /**
     * Genshin Materials, this is used for getting the experience needed to level up an artifact.
     */
    GENSHIN_ARTIFACT_COSTS,
    /**
     * Honkai: Star Rail Characters, please read the documentation on {@link EnkaCache#GENSHIN_CHARACTERS}.
     */
    HONKAI_CHARACTERS
}
