package me.kazury.enkanetworkapi.enka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.starrail.data.SRCharacterData;
import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.util.exceptions.NoLocalizationFound;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import me.kazury.enkanetworkapi.util.GameType;
import me.kazury.enkanetworkapi.util.GlobalLocalization;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * A class which contains all caches that are needed for the API
 */
public class EnkaCaches {
    private static final Map<Integer, String> namecardCache = new HashMap<>();
    private static final Map<String, GenshinCharacterData> characterCache = new HashMap<>();
    private static final Map<Long, GenshinProfilePicture> genshinProfiles = new HashMap<>();

    private static final Map<String, SRCharacterData> srCharacterDataCache = new HashMap<>();
    private static final Map<String, GenshinAffix> affixCache = new HashMap<>();


    private static final Map<GlobalLocalization, JsonNode> genshinLocalizationCache = new HashMap<>();
    private static final Map<GlobalLocalization, JsonNode> honkaiLocalizationCache = new HashMap<>();

    private static final OkHttpClient client = new OkHttpClient();
    private static final Map<String, JsonNode> materialCache = new HashMap<>();

    private static final JsonNode materialJsonNode;

    private static boolean genshinLoadedOrLoading = false;
    private static boolean honkaiLoadedOrLoading = false;

    static {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("[Cache] Loading... Some tasks may be delayed during this.");
        System.out.println("[Cache] Expect this to take some seconds. (this will be once and same for localization assets.)");

        try (InputStream stream = classLoader.getResourceAsStream("genshinnamecards.json")) {
            if (stream == null) throw new NullPointerException("genshinnamecards.json is null");

            loadCache(stream, (entry, mapper) -> {
                final int id = Integer.parseInt(entry.getKey());
                final JsonNode value = entry.getValue();

                if (!value.has("icon")) return;
                namecardCache.put(id, value.get("icon").asText());
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (InputStream stream = classLoader.getResourceAsStream("genshincharacters.json")) {
            if (stream == null) throw new NullPointerException("genshincharacters.json is null");

            loadCache(stream, (entry, mapper) -> {
                final String key = entry.getKey();
                final JsonNode value = entry.getValue();

                if (value.isEmpty()) {
                    // some characters (non-implemented travelers) have empty data
                    return;
                }

                characterCache.put(key, mapper.convertValue(value, GenshinCharacterData.class));
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (InputStream stream = classLoader.getResourceAsStream("honkercharacters.json")) {
            if (stream == null) throw new NullPointerException("honkercharacters.json is null");

            loadCache(stream, (entry, mapper) -> {
                final String key = entry.getKey();
                final JsonNode value = entry.getValue();

                if (value.isEmpty()) {
                    // looking at the json, this never seems to happen but just to be sure.
                    return;
                }

                srCharacterDataCache.put(key, mapper.convertValue(value, SRCharacterData.class));
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (InputStream stream = classLoader.getResourceAsStream("genshinprofiles.json")) {
            if (stream == null) throw new NullPointerException("genshinprofiles.json is null");

            final ObjectMapper mapper = new ObjectMapper();
            final ArrayNode node = mapper.readValue(stream, ArrayNode.class);

            for (JsonNode profile : node) {
                genshinProfiles.put(profile.get("id").asLong(), new GenshinProfilePicture(profile));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (InputStream stream = classLoader.getResourceAsStream("genshinaffixes.json")) {
            if (stream == null) throw new NullPointerException("genshinaffixes.json is null");

            loadCache(stream, (entry, mapper) -> {
                final String key = entry.getKey();
                final JsonNode value = entry.getValue();

                affixCache.put(key, mapper.convertValue(value, GenshinAffix.class));
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        materialJsonNode = fetchJsonData(GameType.GENSHIN,"ExcelBinOutput", "MaterialExcelConfigData.json");
        System.out.println(materialJsonNode.size());
        System.out.println("[Cache] All caches have loaded.");
    }

    public static void main(String[] args) {
        final EnkaNetworkAPI api = new EnkaNetworkAPI();

        api.fetchGenshinUser(722777337, (user) -> {
            GenshinUserInformation user1 = user.toGenshinUser();

            System.out.println(api.getGenshinMaterial(113023).getDescription());
        });
    }

    /**
     * Loads a cache and then applies the given action.
     */
    protected static void loadCache(@NotNull InputStream stream,
                                    @NotNull BiConsumer<Map.Entry<String, JsonNode>, ObjectMapper> then) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode jsonNode = mapper.readValue(stream, JsonNode.class);

        jsonNode.fields().forEachRemaining((entry) -> then.accept(entry, mapper));
    }

    /**
     * Fetches json data from gitlab.
     *
     * @param subPath  the sub path
     * @param fileName the file name
     * @return the json node
     */
    @Nullable
    protected static JsonNode fetchJsonData(@NotNull GameType game, @NotNull String subPath, @NotNull String fileName) {
        fileName = parseString(fileName);

        final ObjectMapper mapper = new ObjectMapper();
        final String url = game.getUrl().formatted(subPath, fileName);
        final Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute();
             ResponseBody body = response.body()) {
            if (body == null) return null;
            return mapper.readValue(body.string(), JsonNode.class);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
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
    protected static GenshinMaterial getMaterial(final int id) {
        final String realId = String.valueOf(id);
        final JsonNode existingMaterial = materialCache.get(realId);

        if (existingMaterial != null) {
            return new GenshinMaterial(existingMaterial);
        }

        final JsonNode node = materialJsonNode.get(id);
        System.out.println(node);

        if (node != null) {
            materialCache.put(realId, node);
            return new GenshinMaterial(node);
        }
        return null;
    }

    /**
     * Fetches a profile icon from the cache
     * <br>You will need to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     * @param pair Pair of id and 0 or Pair of avatarId and costumeId
     * @return the profile icon name
     * @since 4.1
     */
    @NotNull
    protected static String getProfileIcon(@NotNull Pair<Long, Long> pair) {
        final long l = fetchLast();

        final long first = pair.getFirst();
        final long second = pair.getSecond();
        if (first > l && second == 0) {
            // user did not migrate yet (did not change profile after 4.1)
            final GenshinCharacterData data = getGenshinCharacterData(String.valueOf(first));
            if (data == null) throw new UpdateLibraryException();
            return data.getIconName();
        }

        if (second == 0) {
            // user has migrated, does not have costume
            return genshinProfiles.get(first).getIconPath();
        }

        // since we are here, we know that the user has migrated and has a costume equipped
        // first = avatar id (character id), second = costume id
        // therefore we need to filter by internal id to find where internal id == second
        return genshinProfiles.values().stream().filter((picture) -> picture.getInternalId() == second)
                .findFirst()
                .orElseThrow(UpdateLibraryException::new) // should never happen unless json is outdated
                .getIconPath();
    }

    /**
     * Fetches the highest profile picture id in the json.
     * @return the highest profile picture id
     */
    protected static long fetchLast() {
        long max = 0L;
        for (long pictureId : genshinProfiles.keySet()) {
            if (pictureId < max) continue;
            max = pictureId;
        }
        return max;
    }

    /**
     * Loads the localization from the json files
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
     * @param map the map
     * @param locale the locale
     * @param id the id
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
     * @param locale the locale
     * @param id the id
     * @return The key from the locale or null if not found.
     */
    @Nullable
    public static String getGenshinLocale(@NotNull GlobalLocalization locale, @NotNull String id) {
        return getLocale(genshinLocalizationCache, locale, id);
    }

    /**
     * Gets a honkai localization from the cache.
     * @param locale the locale
     * @param id the id
     * @return The key from the locale or null if not found.
     */
    @Nullable
    public static String getHonkaiLocale(@NotNull GlobalLocalization locale, @NotNull String id) {
        return getLocale(honkaiLocalizationCache, locale, id);
    }

    /**
     * Gets a genshin affix from the cache.
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
