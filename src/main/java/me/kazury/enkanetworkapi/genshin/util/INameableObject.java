package me.kazury.enkanetworkapi.genshin.util;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import me.kazury.enkanetworkapi.genshin.data.GenshinLocalization;
import org.jetbrains.annotations.Nullable;

public interface INameableObject {
    /**
     * Gets the name of this object given by a locale.
     * <br>If the locale is {@code null}, then the default locale will be used.
     * @param locale The locale.
     * @return The name of the character.
     */
    default String getName(@Nullable GenshinLocalization locale) {
        locale = EnkaGlobals.parseLocalization(locale);
        return EnkaCaches.getLocale(locale, getNameTextMapHash());
    }

    /**
     * Gets the name of this object given by the default locale.
     * @return The name of the character.
     */
    default String getName() {
        return this.getName(null);
    }

    String getNameTextMapHash();
}
