package me.kazury.enkanetworkapi.starrail.util;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import me.kazury.enkanetworkapi.util.GlobalLocalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TODO - Add documentation
 */
public interface SRNameable {
    /**
     * TODO - Add documentation
     */
    default String getName(@Nullable GlobalLocalization locale) {
        locale = EnkaGlobals.parseLocalization(locale);
        return EnkaCaches.getHonkaiLocale(locale, this.getNameHash());
    }

    /**
     * TODO - Add documentation
     */
    default String getName() {
        return this.getName(null);
    }

    /**
     * TODO - Add documentation
     */
    @NotNull
    String getNameHash();
}
