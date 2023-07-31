package me.kazury.enkanetworkapi.enka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kazury.enkanetworkapi.genshin.data.GenshinCharacter;
import me.kazury.enkanetworkapi.genshin.data.GenshinLocalization;
import me.kazury.enkanetworkapi.genshin.exceptions.NoLocalizationFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EnkaCaches {
    private final Map<Integer, String> namecardCache = new HashMap<>();
    private final Map<String, GenshinCharacter> characterCache = new HashMap<>();
    private final Map<String, Map<String, String>> localeCache = new HashMap<>();

    public EnkaCaches() {
        this.loadCaches();
        
        System.out.println("Loaded " + namecardCache.size() + " namecards.");
        System.out.println("Loaded " + characterCache.size() + " characters.");
        System.out.println("Loaded " + localeCache.size() + " locales. (with " + localeCache.values().stream().mapToInt(Map::size).sum() + " entries)");
    }

    private void loadCaches() {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream in = classLoader.getResourceAsStream("namecards.json")){
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
                final int id = Integer.parseInt(entry.getKey());
                final JsonNode value = entry.getValue();

                if (!value.has("icon")) return;
                this.namecardCache.put(id, value.get("icon").asText());
            });
        } catch (IOException exception){
            exception.printStackTrace();
        }

        try (InputStream in = classLoader.getResourceAsStream("characters.json")) {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode jsonNode = mapper.readValue(in, JsonNode.class);

            jsonNode.fields().forEachRemaining((entry) -> {
                final String key = entry.getKey();
                final JsonNode value = entry.getValue();

                final GenshinCharacter character = mapper.convertValue(value, GenshinCharacter.class);
                this.characterCache.put(key, character);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

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

                this.localeCache.put(key, locale);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public boolean hasNamecard(final int id) {
        return namecardCache.containsKey(id);
    }

    public boolean hasCharacter(@NotNull String id) {
        return characterCache.containsKey(id);
    }

    @NotNull
    public String getLocale(@Nullable GenshinLocalization locale, @NotNull String id) {
        if (locale == null) throw new NoLocalizationFoundException();
        return this.localeCache.getOrDefault(locale.getCode(), new HashMap<>()).getOrDefault(id, "Translation not found for " + id + " in " + locale);
    }

    @NotNull
    public String getLocale(@Nullable GenshinLocalization locale, final long id) {
        return this.getLocale(locale, String.valueOf(id));
    }

    @Nullable
    public GenshinCharacter getCharacterData(@NotNull String id) {
        return this.characterCache.get(id);
    }

    @NotNull
    public String getNamecardName(final int id) {
        return this.namecardCache.getOrDefault(id, "UI_NameCardPic_0_P");
    }
}
