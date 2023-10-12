package me.kazury.enkanetworkapi.starrail.util;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import me.kazury.enkanetworkapi.starrail.data.SRCharacterData;
import me.kazury.enkanetworkapi.starrail.data.SRRelic;
import me.kazury.enkanetworkapi.util.GlobalLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A functional interface which is used for getting names of objects
 * @see SRRelic
 * @see SRCharacterData
 */
public interface SRNameable {
    /**
     * Gets the name of this object given by a locale.
     * <br>There are some keys, which do not exist so this will return {@code null} if the key does not exist.
     * <br>If the locale is {@code null}, then the default locale will be used.
     * @param locale The locale.
     * @return The name of this object.
     * @see SRRelic
     * @see SRCharacterData
     */
    @Nullable
    default String getName(@Nullable GlobalLocalization locale) {
        locale = EnkaGlobals.parseLocalization(locale);
        return EnkaCaches.getHonkaiLocale(locale, this.getNameHash());
    }

    /**
     * Gets the name of this object given by the default locale.
     * <br>There are some keys, which do not exist so this will return {@code null} if the key does not exist.
     * @return The name of this object.
     * @see SRRelic
     * @see SRCharacterData
     */
    @Nullable
    default String getName() {
        return this.getName(null);
    }

    @NotNull
    String getNameHash();
}
