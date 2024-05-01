package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.genshin.data.GenshinUserInformation;
import me.kazury.enkanetworkapi.starrail.data.SRUserCharacter;
import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.genshin.data.GenshinArtifact;
import me.kazury.enkanetworkapi.genshin.data.GenshinUserWeapon;
import me.kazury.enkanetworkapi.genshin.data.GenshinUserCharacter;

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
     * @see GenshinArtifact#getAscensionData(int)
     * @see GenshinUserWeapon#getAscensionData(int)
     * @see GenshinUserCharacter#getAscensionData(int)
     */
    GENSHIN_MATERIALS,
    /**
     * Genshin Artifact costs, this is used for getting the experience needed to level up an artifact.
     */
    GENSHIN_ARTIFACT_COSTS,
    /**
     * Genshin Avatar Configs, this is used to parse a character id into a promote id.
     * <br>You can block this if you do not need character ascension materials.
     */
    GENSHIN_AVATAR_CONFIGS,
    /**
     * Genshin Ascension Materials, this is used to get the materials needed for character ascension.
     * <br>Note: If {@link #GENSHIN_MATERIALS} is blocked, this will not work.
     */
    GENSHIN_CHARACTER_ASCENSION_MATERIALS,
    /**
     * Genshin Weapon Configs, this is used to parse a weapon id into a promote id.
     * <br>You can block this if you do not need weapon ascension materials.
     */
    GENSHIN_WEAPON_CONFIGS,
    /**
     * Genshin Weapon Ascension Materials, this is used to get the materials needed for weapon ascension.
     * <br>Note: If {@link #GENSHIN_MATERIALS} is blocked, this will not work.
     */
    GENSHIN_WEAPON_ASCENSION_MATERIALS,
    /**
     * The metadata for some characters, this is for example used if you want to fetch the properties of a character.
     * <br>Unlike genshin, we need to calculate the stats ourselves and not exposed via the json.
     */
    HONKAI_META,
    /**
     * Lightcone data for Honkai: Star Rail.
     */
    HONKAI_LIGHTCONES,
    /**
     * Honkai: Star Rail Characters, please read the documentation on {@link EnkaCache#GENSHIN_CHARACTERS}.
     */
    HONKAI_CHARACTERS
}
