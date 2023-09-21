package me.kazury.enkanetworkapi.enka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.genshin.exceptions.NoLocalizationFound;
import me.kazury.enkanetworkapi.util.GameType;
import me.kazury.enkanetworkapi.util.GlobalLocalization;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A class which contains all caches that are needed for the API
 */
public class EnkaCaches {
    private static final Map<Integer, String> namecardCache = new HashMap<>();
    private static final Map<String, GenshinCharacterData> characterCache = new HashMap<>();

    private static final Map<GlobalLocalization, JsonNode> genshinLocalizationCache = new HashMap<>();
    private static final Map<GlobalLocalization, JsonNode> honkaiLocalizationCache = new HashMap<>();

    private static final OkHttpClient client = new OkHttpClient();
    private static final Map<String, JsonNode> materialCache = new HashMap<>();

    private static final JsonNode materialJsonNode;

    static {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println("Loading caches... Some tasks may be delayed during this.");
        System.out.println("Expect this to take around 10 seconds. (this will be once and same for localization assets.)");

        try (InputStream in = classLoader.getResourceAsStream("namecards.json")) {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
                final int id = Integer.parseInt(entry.getKey());
                final JsonNode value = entry.getValue();

                if (!value.has("icon")) return;
                namecardCache.put(id, value.get("icon").asText());
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try (InputStream in = classLoader.getResourceAsStream("characters.json")) {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
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

        materialJsonNode = fetchJsonData(GameType.GENSHIN,"ExcelBinOutput", "MaterialExcelConfigData.json");
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
        System.out.println(url);

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

        if (node != null) {
            materialCache.put(realId, node);
            return new GenshinMaterial(node);
        }
        return null;
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
        final JsonNode node = genshinLocalizationCache.get(localization);
        if (node != null) return;

        System.out.println("(Genshin) New localization (" + localization.name() + ") has been detected, loading...");
        final JsonNode langNode = fetchJsonData(GameType.GENSHIN, "TextMap", "TextMap" + localization.getCode());
        genshinLocalizationCache.put(localization, langNode);
        System.out.println("(Genshin) Localization has been loaded!");
    }

    /**
     * Loads honkai localizations from the Excel.
     */
    protected static void loadHonkaiLocalization(@NotNull GlobalLocalization localization) {
        final JsonNode node = honkaiLocalizationCache.get(localization);
        if (node != null) return;

        System.out.println("(Honkai) New localization (" + localization.name() + ") has been detected, loading...");
        final JsonNode langNode = fetchJsonData(GameType.HONKAI, "TextMap", "TextMap" + localization.getCode());
        honkaiLocalizationCache.put(localization, langNode);
        System.out.println("(Honkai) Localization has been loaded!");
    }

    /**
     * Checks if the namecard cache contains a namecard.
     *
     * @param id the namecard id
     * @return true if the namecard cache contains the namecard
     */
    public static boolean hasNamecard(final int id) {
        return namecardCache.containsKey(id);
    }

    /**
     * Gets character data from the cache.
     *
     * @param id the character id
     * @return the character data
     */
    @Nullable
    public static GenshinCharacterData getCharacterData(@NotNull String id) {
        return characterCache.getOrDefault(id, null);
    }

    /**
     * @return A copy of the character cache.
     */
    @NotNull
    protected static Map<String, GenshinCharacterData> getCharacterMap() {
        return Map.copyOf(characterCache);
    }

    @NotNull
    public static String getGenshinLocale(@NotNull GlobalLocalization locale, @NotNull String id) {
        final JsonNode node = genshinLocalizationCache.get(locale);

        if (node == null) {
            // IntelliJ complains that the #get call below this might throw
            throw new NoLocalizationFound();
        }

        final JsonNode langNode = node.get(id);
        if (langNode == null) throw new NoLocalizationFound();
        return langNode.asText();
    }

    @NotNull
    public static String getHonkaiLocale(@NotNull GlobalLocalization locale, @NotNull String id) {
        final JsonNode node = honkaiLocalizationCache.get(locale);

        if (node == null) {
            // IntelliJ complains that the #get call below this might throw
            throw new NoLocalizationFound();
        }

        final JsonNode langNode = node.get(id);
        if (langNode == null) throw new NoLocalizationFound();
        return langNode.asText();
    }

    @NotNull
    public static String getNamecardName(final int id) {
        return namecardCache.getOrDefault(id, "UI_NameCardPic_0_P");
    }
}
