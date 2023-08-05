package me.kazury.enkanetworkapi.enka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kazury.enkanetworkapi.genshin.data.GenshinCharacterData;
import me.kazury.enkanetworkapi.genshin.data.GenshinLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EnkaCaches {
    private static final Map<Integer, String> namecardCache = new HashMap<>();
    private static final Map<String, GenshinCharacterData> characterCache = new HashMap<>();
    private static final Map<String, Map<String, String>> localeCache = new HashMap<>();

    static {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream in = classLoader.getResourceAsStream("namecards.json")){
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
                final int id = Integer.parseInt(entry.getKey());
                final JsonNode value = entry.getValue();

                if (!value.has("icon")) return;
                namecardCache.put(id, value.get("icon").asText());
            });
        } catch (IOException exception){
            exception.printStackTrace();
        }
        System.out.println("Loaded " + namecardCache.size() + " namecards.");

        try (InputStream in = classLoader.getResourceAsStream("characters.json")) {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
                final String key = entry.getKey();
                final JsonNode value = entry.getValue();

                final GenshinCharacterData character = mapper.convertValue(value, GenshinCharacterData.class);

                if (value.isEmpty()) {
                    // some characters (non-implemented travelers) have empty data
                    return;
                }

                characterCache.put(key, character);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Loaded " + characterCache.size() + " characters.");

        try (InputStream in = classLoader.getResourceAsStream("localization.json")) {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
                final String key = entry.getKey();
                final JsonNode value = entry.getValue();

                final Map<String, String> locale = new HashMap<>();
                value.fields().forEachRemaining((localeEntry) -> {
                    final String localeKey = localeEntry.getKey();
                    final String localeValue = localeEntry.getValue().asText();

                    locale.put(localeKey, localeValue);
                });

                localeCache.put(key, locale);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Loaded " + localeCache.size() + " locales. (with " + localeCache.values().stream().mapToInt(Map::size).sum() + " entries)");
    }

    public static boolean hasNamecard(final int id) {
        return namecardCache.containsKey(id);
    }

    public static boolean hasCharacter(@NotNull String id) {
        return characterCache.containsKey(id);
    }

    @Nullable
    public static GenshinCharacterData getCharacterData(@NotNull String id) {
        return characterCache.getOrDefault(id, null);
    }

    @NotNull
    public static Map<String, GenshinCharacterData> getCharacterMap() {
        return Map.copyOf(characterCache);
    }

    @NotNull
    public static String getLocale(@NotNull GenshinLocalization locale, @NotNull String id) {
        return localeCache.getOrDefault(locale.getCode(), new HashMap<>()).getOrDefault(id, "No translation (" + id + ", " + locale + ")");
    }

    @NotNull
    public static String getLocale(@NotNull GenshinLocalization locale, final long id) {
        return getLocale(locale, String.valueOf(id));
    }

    @NotNull
    public static String getNamecardName(final int id) {
        return namecardCache.getOrDefault(id, "UI_NameCardPic_0_P");
    }
}
