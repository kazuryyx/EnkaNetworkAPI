package me.kazury.enkanetworkapi.enka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.genshin.exceptions.NoLocalizationFound;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A class which contains all caches that are needed for the API
 */
public class EnkaCaches {
    private static final Map<Integer, String> namecardCache = new HashMap<>();
    private static final Map<String, GenshinCharacterData> characterCache = new HashMap<>();

    private static final Map<GenshinLocalization, JsonNode> localizationCache = new HashMap<>();
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

        materialJsonNode = fetchJsonData("ExcelBinOutput", "MaterialExcelConfigData.json");
    }

    /**
     * Fetches json data from gitlab.
     *
     * @param subPath  the sub path
     * @param fileName the file name
     * @return the json node
     */
    @Nullable
    protected static JsonNode fetchJsonData(@NotNull String subPath, @NotNull String fileName) {
        fileName = parseString(fileName);

        final ObjectMapper mapper = new ObjectMapper();
        final Request request = new Request.Builder()
                .url("https://gitlab.com/Dimbreath/AnimeGameData/-/raw/master/%s/%s".formatted(subPath, fileName))
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

        if (materialJsonNode == null) return null;
        if (!materialJsonNode.isArray()) return null;
        JsonNode materialData = null;

        for (JsonNode entry : materialJsonNode) {
            if (!entry.has("id")) continue;
            if (entry.get("id").asInt() != id) continue;
            materialData = entry;
            break;
        }

        if (materialData != null) {
            materialCache.put(realId, materialData);
            return new GenshinMaterial(materialData);
        }
        return null;
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
    public static String getLocale(@NotNull GenshinLocalization locale, @NotNull String id) {
        JsonNode node = localizationCache.get(locale);

        if (node == null) {
            node = fetchJsonData("TextMap", "TextMap" + locale.getCode());
            localizationCache.put(locale, node);
        }

        if (node == null) throw new NoLocalizationFound();

        final Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

        while (fields.hasNext()) {
            final Map.Entry<String, JsonNode> entry = fields.next();
            if (!entry.getKey().equals(id)) continue;
            return entry.getValue().asText();
        }
        throw new NoLocalizationFound();
    }

    @NotNull
    public static String getNamecardName(final int id) {
        return namecardCache.getOrDefault(id, "UI_NameCardPic_0_P");
    }
}
