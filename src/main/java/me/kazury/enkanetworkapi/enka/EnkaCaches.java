package me.kazury.enkanetworkapi.enka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.starrail.data.SRCharacterData;
import me.kazury.enkanetworkapi.starrail.data.SRLightconeData;
import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.util.exceptions.NoLocalizationFound;
import me.kazury.enkanetworkapi.util.GameType;
import me.kazury.enkanetworkapi.util.GlobalLocalization;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A class which contains all caches that are needed for the API
 */
public class EnkaCaches {
    // genshin
    private static final Map<Integer, String> namecardCache = new HashMap<>();
    private static final Map<String, GenshinCharacterData> characterCache = new HashMap<>();
    private static final Map<String, GenshinAvatarProfile> genshinProfiles = new HashMap<>();

    private static final Map<String, GenshinAffix> affixCache = new HashMap<>();
    private static final Map<String, GenshinMaterial> materialCache = new HashMap<>();

    private static final Map<GlobalLocalization, JsonNode> genshinLocalizationCache = new HashMap<>();

    private static final List<GenshinArtifactRequirement> artifactRequirements = new ArrayList<>();
    private static final List<GenshinCharacterConfig> genshinCharacterConfigs = new ArrayList<>();
    private static final List<GenshinCharacterAscension> genshinCharacterAscensions = new ArrayList<>();
    private static final List<GenshinWeaponAscension> genshinWeaponAscensions = new ArrayList<>();
    private static final List<GenshinWeaponConfig> genshinWeaponConfigs = new ArrayList<>();

    private static boolean genshinLoadedOrLoading = false;

    // star rail
    private static final Map<String, SRCharacterData> srCharacterDataCache = new HashMap<>();
    private static final Map<GlobalLocalization, JsonNode> honkaiLocalizationCache = new HashMap<>();

    private static final Map<String, SRLightconeData> honkaiLightConeCache = new HashMap<>();
    private static final Map<String, String> honkaiProfileCache = new HashMap<>();

    private static final Map<String, JsonNode> metaCharacterCache = new HashMap<>();
    private static final Map<String, JsonNode> metaWeaponCache = new HashMap<>();
    private static final Map<String, JsonNode> metaRelicSetCache = new HashMap<>();
    private static final Map<String, JsonNode> metaWeaponAffixCache = new HashMap<>();
    private static final Map<String, JsonNode> metaSkillTreeCache = new HashMap<>();

    private static boolean honkaiLoadedOrLoading = false;

    // util
    private static final OkHttpClient client = new OkHttpClient();

    public static void loadCaches() {
        namecardCache.clear();
        characterCache.clear();
        genshinProfiles.clear();
        affixCache.clear();
        materialCache.clear();
        artifactRequirements.clear();
        srCharacterDataCache.clear();
        genshinCharacterConfigs.clear();
        genshinCharacterAscensions.clear();
        genshinWeaponAscensions.clear();
        genshinWeaponConfigs.clear();
        metaCharacterCache.clear();
        metaWeaponCache.clear();
        metaRelicSetCache.clear();
        metaWeaponAffixCache.clear();
        metaSkillTreeCache.clear();
        honkaiLightConeCache.clear();
        honkaiProfileCache.clear();

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("[Cache] Loading... Some tasks may be delayed during this.");
        System.out.println("[Cache] Expect this to take some seconds. (this will be once and same for localization assets.)");

        loadNamecardCache(classLoader);
        loadGenshinCharacterCache(classLoader);
        loadHonkaiCharacterCache(classLoader);
        loadGenshinProfileCache(classLoader);
        loadGenshinAffixesCache(classLoader);
        loadHonkaiMetaCharacters(classLoader);
        loadHonkaiMetaWeapons(classLoader);
        loadHonkaiMetaWeaponAffixes(classLoader);
        loadHonkaiMetaRelicSets(classLoader);
        loadHonkaiMetaSkillTree(classLoader);
        loadHonkaiLightconeCache(classLoader);
        loadHonkaiProfileCache(classLoader);
        loadGenshinMaterialCache();
        loadArtifactCostCache();
        loadGenshinAvatarConfigs();
        loadGenshinCharacterAscensions();
        loadGenshinWeaponAscensions();
        loadGenshinWeaponConfigs();

        System.out.println("[Cache] All caches have loaded.");
    }

    private static void baseResourceLoad(@NotNull ClassLoader classLoader, @NotNull String path,
                                         @NotNull Consumer<InputStream> after, @NotNull EnkaCache blockedCache) {
        if (EnkaGlobals.isCacheBlocked(blockedCache)) return;

        try (InputStream stream = classLoader.getResourceAsStream(path)) {
            if (stream == null) throw new NullPointerException(path + " is null.");

            after.accept(stream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void baseFetchLoad(@NotNull GameType type, @NotNull String subPath, @NotNull String fileName,
                                      @NotNull BiConsumer<ObjectMapper, ArrayNode> jsonConsumer, @NotNull EnkaCache blockedCache) {
        if (EnkaGlobals.isCacheBlocked(blockedCache)) return;

        final String json = fetchRawJsonData(type, subPath, fileName);

        if (json == null) {
            System.out.println("[Cache] Failed to load" + fileName);
            return;
        }

        final ObjectMapper mapper = new ObjectMapper();
        ArrayNode node = null;
        try {
            node = mapper.readValue(json, ArrayNode.class);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
        if (node == null) return;

        jsonConsumer.accept(mapper, node);
    }

    private static void loadGenshinWeaponAscensions() {
        baseFetchLoad(GameType.GENSHIN, "ExcelBinOutput", "WeaponPromoteExcelConfigData.json", (mapper, node) -> {
            for (JsonNode asc : node) {
                final GenshinWeaponAscension ascension = mapper.convertValue(asc, GenshinWeaponAscension.class);
                genshinWeaponAscensions.add(ascension);
            }
        }, EnkaCache.GENSHIN_WEAPON_ASCENSION_MATERIALS);
    }

    private static void loadGenshinWeaponConfigs() {
        baseFetchLoad(GameType.GENSHIN, "ExcelBinOutput", "WeaponExcelConfigData.json", (mapper, node) -> {
            for (JsonNode weapon : node) {
                final GenshinWeaponConfig config = mapper.convertValue(weapon, GenshinWeaponConfig.class);
                genshinWeaponConfigs.add(config);
            }
        }, EnkaCache.GENSHIN_WEAPON_CONFIGS);
    }

    private static void loadGenshinCharacterAscensions() {
        baseFetchLoad(GameType.GENSHIN, "ExcelBinOutput", "AvatarPromoteExcelConfigData.json", (mapper, node) -> {
            for (JsonNode asc : node) {
                final GenshinCharacterAscension ascension = mapper.convertValue(asc, GenshinCharacterAscension.class);
                genshinCharacterAscensions.add(ascension);
            }
        }, EnkaCache.GENSHIN_CHARACTER_ASCENSION_MATERIALS);
    }

    private static void loadArtifactCostCache() {
        baseFetchLoad(GameType.GENSHIN, "ExcelBinOutput", "ReliquaryLevelExcelConfigData.json", (mapper, node) -> {
            for (JsonNode artifact : node) {
                final GenshinArtifactRequirement requirement = mapper.convertValue(artifact, GenshinArtifactRequirement.class);
                artifactRequirements.add(requirement);
            }
        }, EnkaCache.GENSHIN_ARTIFACT_COSTS);
    }

    private static void loadGenshinMaterialCache() {
        baseFetchLoad(GameType.GENSHIN, "ExcelBinOutput", "MaterialExcelConfigData.json", (mapper, node) -> {
            for (JsonNode mat : node) {
                final String key = mat.get("id").asText();
                materialCache.put(key, mapper.convertValue(mat, GenshinMaterial.class));
            }
        }, EnkaCache.GENSHIN_MATERIALS);
    }

    private static void loadGenshinAvatarConfigs() {
        baseFetchLoad(GameType.GENSHIN, "ExcelBinOutput", "AvatarExcelConfigData.json", (mapper, node) -> {
            for (JsonNode avatar : node) {
                final GenshinCharacterConfig config = mapper.convertValue(avatar, GenshinCharacterConfig.class);
                genshinCharacterConfigs.add(config);
            }
        }, EnkaCache.GENSHIN_AVATAR_CONFIGS);
    }

    private static void loadHonkaiMetaSkillTree(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkermeta_skilltree.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode node = entry.getValue();

            metaSkillTreeCache.put(key, node);
        }), EnkaCache.HONKAI_META);
    }

    private static void loadHonkaiMetaWeaponAffixes(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkermeta_weapons_affix.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode node = entry.getValue();

            metaWeaponAffixCache.put(key, node);
        }), EnkaCache.HONKAI_META);
    }

    private static void loadHonkaiMetaRelicSets(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkermeta_weapons.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode node = entry.getValue();

            metaWeaponCache.put(key, node);
        }), EnkaCache.HONKAI_META);
    }

    private static void loadHonkaiMetaWeapons(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkermeta_relic_sets.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode node = entry.getValue();

            metaRelicSetCache.put(key, node);
        }), EnkaCache.HONKAI_META);
    }

    private static void loadHonkaiMetaCharacters(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkermeta_characters.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode node = entry.getValue();

            metaCharacterCache.put(key, node);
        }), EnkaCache.HONKAI_META);
    }

    private static void loadGenshinAffixesCache(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "genshinaffixes.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode value = entry.getValue();

            affixCache.put(key, mapper.convertValue(value, GenshinAffix.class));
        }), EnkaCache.GENSHIN_AFFIXES);
    }

    private static void loadGenshinProfileCache(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "genshinprofiles.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode value = entry.getValue();

            genshinProfiles.put(key, mapper.convertValue(value, GenshinAvatarProfile.class));
        }), EnkaCache.GENSHIN_PROFILES);
    }

    private static void loadHonkaiLightconeCache(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkerlightcones.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final SRLightconeData data = mapper.convertValue(entry.getValue(), SRLightconeData.class);

            honkaiLightConeCache.put(key, data);
        }), EnkaCache.HONKAI_LIGHTCONES);
    }

    private static void loadHonkaiCharacterCache(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "honker/honkercharacters.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode value = entry.getValue();

            if (value.isEmpty()) {
                // looking at the json, this never seems to happen but just to be sure.
                return;
            }
            final SRCharacterData data = mapper.convertValue(value, SRCharacterData.class);
            data.setCharacterId(key);

            srCharacterDataCache.put(key, data);
        }), EnkaCache.HONKAI_CHARACTERS);
    }

    private static void loadHonkaiProfileCache(@NotNull ClassLoader loader) {
        baseResourceLoad(loader, "honker/honkeravatars.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode value = entry.getValue();

            honkaiProfileCache.put(key, value.asText());
        }), EnkaCache.HONKAI_PROFILES);
    }

    private static void loadGenshinCharacterCache(@NotNull ClassLoader classLoader) {
        baseResourceLoad(classLoader, "genshincharacters.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final String key = entry.getKey();
            final JsonNode value = entry.getValue();

            if (value.isEmpty()) {
                // some characters (non-implemented travelers) have empty data
                return;
            }
            final GenshinCharacterData data = mapper.convertValue(value, GenshinCharacterData.class);
            data.setCharacterId(key);

            characterCache.put(key, data);
        }), EnkaCache.GENSHIN_CHARACTERS);
    }

    private static void loadNamecardCache(@NotNull ClassLoader classLader) {
        baseResourceLoad(classLader, "genshinnamecards.json", (stream) -> loadCache(stream, (entry, mapper) -> {
            final int id = Integer.parseInt(entry.getKey());
            final JsonNode value = entry.getValue();

            if (!value.has("icon")) return;
            namecardCache.put(id, value.get("icon").asText());
        }), EnkaCache.GENSHIN_NAMECARDS);
    }

    /**
     * Loads a cache and then applies the given action.
     */
    protected static void loadCache(@NotNull InputStream stream,
                                    @NotNull BiConsumer<Map.Entry<String, JsonNode>, ObjectMapper> then) {
        final ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readValue(stream, JsonNode.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        if (jsonNode == null) return;
        jsonNode.fields().forEachRemaining((entry) -> then.accept(entry, mapper));
    }

    /**
     * Fetches json data from gitlab.
     *
     * @param subPath  the sub path
     * @param fileName the file name
     * @return the json
     */
    @Nullable
    protected static String fetchRawJsonData(@NotNull GameType game, @NotNull String subPath, @NotNull String fileName) {
        fileName = parseString(fileName);

        final String url = game.getUrl().formatted(subPath, fileName);
        final Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute();
             ResponseBody body = response.body()) {
            if (body == null) return null;
            return body.string();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * Fetches json data from gitlab.
     *
     * @param game     the game
     * @param subPath  the sub path
     * @param fileName the file name
     * @return the json
     */
    @NotNull
    protected static JsonNode fetchJsonData(@NotNull GameType game, @NotNull String subPath, @NotNull String fileName) {
        fileName = parseString(fileName);

        final ObjectMapper mapper = new ObjectMapper();
        final String url = game.getUrl().formatted(subPath, fileName);
        final Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute();
             ResponseBody body = response.body()) {
            if (body == null) throw new IOException("Response body is null");
            return mapper.readValue(body.string(), JsonNode.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        throw new NullPointerException("Response body is null");
    }

    /**
     * Parses a string and adds the .json extension if it does not exist.
     *
     * @param fileName the file name
     * @return the parsed string, "character" -> "character.json"
     */
    @NotNull
    protected static String parseString(@NotNull String fileName) {
        return fileName.endsWith(".json") ? fileName : fileName + ".json";
    }

    /**
     * Gets the HTTP client which will be used for requests.
     *
     * @return the HTTP client
     */
    @NotNull
    protected static OkHttpClient getClient() {
        return client;
    }

    /**
     * Gets a material from the cache.
     *
     * @param id the material id
     * @return the material
     */
    @Nullable
    public static GenshinMaterial getMaterial(final int id) {
        return materialCache.getOrDefault(String.valueOf(id), null);
    }

    /**
     * Fetches a profile icon from the cache
     *
     * @param pair the profile picture pair
     * @return the profile icon name
     */
    @NotNull
    protected static String getProfileIcon(@NotNull Pair<Long, Long> pair) {
        // check if the player still has avatarId field
        // this method is a mess
        final long first = pair.getFirst();
        final long second = pair.getSecond();

        if (first != 0L) {
            if (second != 0L) {
                // costume
                return genshinProfiles.values()
                        .stream()
                        .filter(profile -> profile.getId() == second)
                        .findFirst()
                        .orElseThrow(UpdateLibraryException::new)
                        .getImage();
            }

            // no costume
            // filter the json by "id" field and return the key
            return genshinProfiles.values()
                    .stream()
                    .filter(profile -> profile.getId() == first)
                    .findFirst()
                    .orElseThrow(UpdateLibraryException::new)
                    .getImage();
        }

        // filter the list by key and return the value
        final GenshinAvatarProfile profile = genshinProfiles.get(String.valueOf(second));
        if (profile == null) throw new UpdateLibraryException();
        return profile.getImage();
    }

    /**
     * Loads the localization from the json files
     *
     * @param localization the localization to load
     */
    protected static void loadLocalizations(@NotNull GlobalLocalization localization) {
        loadGenshinLocalizations(localization);
        loadHonkaiLocalization(localization);
    }

    /**
     * Loads genshin localizations from the Excel.
     */
    protected static void loadGenshinLocalizations(@NotNull GlobalLocalization localization) {
        if (genshinLoadedOrLoading) return;
        final JsonNode node = genshinLocalizationCache.get(localization);
        if (node != null) return;

        genshinLoadedOrLoading = true;
        System.out.println("[Localization::Genshin] New localization (" + localization.name() + ") has been detected, loading...");
        final JsonNode langNode = fetchJsonData(GameType.GENSHIN, "TextMap", "TextMap" + localization.getCode());
        genshinLocalizationCache.put(localization, langNode);
        System.out.println("[Localization::Genshin] Localization has been loaded!");
    }

    /**
     * Loads honkai localizations from the Excel.
     */
    protected static void loadHonkaiLocalization(@NotNull GlobalLocalization localization) {
        if (!EnkaGlobals.isHonkaiEnabled() && !honkaiLoadedOrLoading) {
            System.out.println("[Localization::Honkai] Currently disabled, skipping loading.");
            honkaiLoadedOrLoading = true;
            return;
        }
        if (honkaiLoadedOrLoading) return;

        final JsonNode node = honkaiLocalizationCache.get(localization);
        if (node != null) return;

        honkaiLoadedOrLoading = true;
        System.out.println("[Localization::Honkai] New localization (" + localization.name() + ") has been detected, loading...");
        final JsonNode langNode = fetchJsonData(GameType.HONKAI, "TextMap", "TextMap" + localization.getCode());
        honkaiLocalizationCache.put(localization, langNode);
        System.out.println("[Localization::Honkai] Localization has been loaded!");
    }

    /**
     * Checks if the namecard cache contains a namecard.
     *
     * @param id the namecard id
     * @return true if the namecard cache contains the namecard
     */
    protected static boolean hasNamecard(final int id) {
        return namecardCache.containsKey(id);
    }

    /**
     * Gets genshin character data from the cache.
     *
     * @param id the character id
     * @return the character data
     */
    @Nullable
    public static GenshinCharacterData getGenshinCharacterData(@NotNull String id) {
        return characterCache.getOrDefault(id, null);
    }

    /**
     * Gets HSR character data from the cache.
     * @param id the character id
     * @return the character data
     */
    @Nullable
    public static SRCharacterData getSRCharacterData(@NotNull String id) {
        return srCharacterDataCache.getOrDefault(id, null);
    }

    /**
     * Gets lightcone data from cache
     * @param lightconeId The lightcone id
     * @return The lightcone data
     */
    @Nullable
    public static SRLightconeData getLightconeData(final long lightconeId) {
        return honkaiLightConeCache.getOrDefault(String.valueOf(lightconeId), null);
    }

    /**
     * Gets the requirements for leveling up a specific artifact.
     */
    @NotNull
    public static List<GenshinArtifactRequirement> getArtifactRequirements() {
        return List.copyOf(artifactRequirements);
    }

    /**
     * Gets the avatar configs for Genshin Impact. Used for converting character id -> promote id.
     */
    @NotNull
    public static List<GenshinCharacterConfig> getGenshinAvatarConfigs() {
        return List.copyOf(genshinCharacterConfigs);
    }

    /**
     * Gets the weapon configs for Genshin Impact. Used for converting weapon id -> promote id.
     */
    @NotNull
    public static List<GenshinWeaponConfig> getGenshinWeaponConfigs() {
        return List.copyOf(genshinWeaponConfigs);
    }

    /**
     * Gets the genshin ascensions.
     * <br>These are used for calculating the amount of materials needed for ascension.
     */
    public static List<GenshinWeaponAscension> getGenshinWeaponAscensions() {
        return List.copyOf(genshinWeaponAscensions);
    }

    /**
     * Gets the genshin ascensions.
     * <br>These are used for calculating the amount of materials needed for ascension.
     */
    public static List<GenshinCharacterAscension> getGenshinCharacterAscensions() {
        return List.copyOf(genshinCharacterAscensions);
    }

    /**
     * @return A list of all genshin materials.
     * <br>This list also contains experimental materials, and not all of them have a description / name.
     */
    @NotNull
    public static List<GenshinMaterial> getMaterials() {
        return List.copyOf(materialCache.values());
    }

    /**
     * @return A copy of the genshin character cache.
     */
    @NotNull
    protected static Map<String, GenshinCharacterData> getCharacterMap() {
        return Map.copyOf(characterCache);
    }

    /**
     * A copy of the SR character cache.
     */
    public static Map<String, SRCharacterData> getSRCharacterMap() {
        return Map.copyOf(srCharacterDataCache);
    }

    /**
     * Gets a localization from the cache.
     *
     * @param map    the map
     * @param locale the locale
     * @param id     the id
     * @return The key from the locale or null if not found.
     */
    @Nullable
    private static String getLocale(@NotNull Map<GlobalLocalization, JsonNode> map,
                                    @NotNull GlobalLocalization locale,
                                    @NotNull String id) {
        final JsonNode node = map.get(locale);

        if (node == null) {
            // IntelliJ complains that the #get call below this might throw
            throw new NoLocalizationFound();
        }

        final JsonNode langNode = node.get(id);
        if (langNode == null) return null;
        return langNode.asText();
    }

    /**
     * Gets a genshin localization from the cache.
     *
     * @param locale the locale
     * @param id     the id
     * @return The key from the locale or null if not found.
     */
    @Nullable
    public static String getGenshinLocale(@NotNull GlobalLocalization locale, @NotNull String id) {
        return getLocale(genshinLocalizationCache, locale, id);
    }

    /**
     * Gets the profile cache from this cache.
     * <br>This will be empty if <code>EnkaGlobals.isHonkaiEnabled()</code> is false or {@link EnkaCache#HONKAI_PROFILES} is blocked.
     * @return the profile cache
     */
    @NotNull
    public static Map<String, String> getHonkaiProfileCache() {
        return Map.copyOf(honkaiProfileCache);
    }

    /**
     * Gets a honkai localization from the cache.
     *
     * @param locale the locale
     * @param id     the id
     * @return The key from the locale or null if not found.
     */
    @Nullable
    public static String getHonkaiLocale(@NotNull GlobalLocalization locale, @NotNull String id) {
        return getLocale(honkaiLocalizationCache, locale, id);
    }

    /**
     * Gets properties from a meta character
     * @param characterId The character id
     * @param currentAscension The character ascension
     * @return The properties.
     */
    @Nullable
    public static JsonNode getHonkaiMetaCharacterProperties(@NotNull String characterId, @NotNull String currentAscension) {
        final JsonNode baseNode = metaCharacterCache.get(characterId);
        if (baseNode == null) return null;
        return baseNode.get(currentAscension);
    }

    /**
     * Gets properties from a meta weapon
     * @param weaponId The weapon id
     * @param currentRank The weapon rank
     * @return The properties.
     */
    @Nullable
    public static JsonNode getHonkaiMetaWeaponProperties(@NotNull String weaponId, @NotNull String currentRank) {
        final JsonNode baseNode = metaWeaponCache.get(weaponId);
        if (baseNode == null) return null;
        return baseNode.get(currentRank);
    }

    /**
     * Gets properties from a meta relic set
     * @param relicSet The relic set id
     * @param count The count of relics, either 2 or 4
     * @return The properties.
     */
    @Nullable
    public static JsonNode getHonkaiMetaRelicSetProperties(final long relicSet, final int count) {
        final JsonNode baseNode = metaRelicSetCache.get(String.valueOf(relicSet));
        if (baseNode == null) return null;
        final JsonNode setNode = baseNode.get(String.valueOf(count));
        if (setNode == null) return null;
        return setNode.get("props");
    }

    /**
     * Gets properties from a meta weapon affix
     * @param weaponId The weapon id
     * @param superImposion The super imposion
     * @return The properties.
     */
    @Nullable
    public static JsonNode getHonkaiMetaWeaponAffixProperties(@NotNull String weaponId, @NotNull String superImposion) {
        final JsonNode baseNode = metaWeaponAffixCache.get(weaponId);
        if (baseNode == null) return null;
        final JsonNode weaponNode = baseNode.get(superImposion);
        if (weaponNode == null) return null;
        return weaponNode.get("props");
    }

    /**
     * Gets properties from a meta skill tree
     * @param traceId The trace id
     * @param level The level
     * @return The properties.
     */
    @Nullable
    public static JsonNode getHonkaiMetaSkillTreeProperties(@NotNull String traceId, @NotNull String level) {
        final JsonNode baseNode = metaSkillTreeCache.get(traceId);
        if (baseNode == null) return null;
        final JsonNode skillNode = baseNode.get(level);
        if (skillNode == null) return null;
        return skillNode.get("props");
    }

    /**
     * Gets a genshin affix from the cache.
     *
     * @param id the affix id
     * @return the affix
     */
    @Nullable
    protected static GenshinAffix getGenshinAffix(@NotNull String id) {
        return affixCache.getOrDefault(id, null);
    }

    @NotNull
    public static String getNamecardName(final int id) {
        return namecardCache.getOrDefault(id, "UI_NameCardPic_0_P");
    }
}
