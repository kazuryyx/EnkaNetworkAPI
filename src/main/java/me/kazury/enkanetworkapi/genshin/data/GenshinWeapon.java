package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaGlobals;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.exceptions.NoLocalizationFoundException;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GenshinWeapon {
    /**
     * Represents a localization key for the weapon name.
     */
    private final String nameTextMapHash;
    /**
     * The level of this weapon, this is a number between 1 and 90
     */
    private final int weaponLevel;
    /**
     * The current ascension of this weapon, this is a number between 0 and 6
     */
    private final int weaponAscension;
    /**
     * The current refinement of this weapon, this is a number between 1 and 5
     * <br>To get refinements on weapons, you need to get a copy of the weapon.
     */
    private final int weaponRefinement;

    /**
     * The icon ID of the weapon
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getIcon(String)}
     */
    private final String weaponIcon;

    /**
     * A weapon can have up to 2 stats
     * <br>One always being the Base ATK, and another one being a different stat (either % stats or flat stats).
     */
    private final List<WeaponStat> stats;

    /**
     * Returns the name of this weapon given by the specified locale.
     * @param locale The locale to get the name from. If {@code null}, then the default locale will be used.
     * @return The name of the weapon.
     * @throws NoLocalizationFoundException If the specified locale is {@code null} and the default locale is {@code null}.
     */
    @Nullable
    public String getWeaponName(@Nullable GenshinLocalization locale) {
        locale = EnkaGlobals.parseLocalization(locale);
        return EnkaCaches.getLocale(locale, this.nameTextMapHash);
    }

    /**
     * Returns the name of this weapon given by the default locale.
     *
     * @return The name of the weapon with the default locale.
     * @throws NoLocalizationFoundException If the default locale is {@code null}.
     */
    public String getWeaponName() {
        return this.getWeaponName(null);
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class WeaponStat {
        /**
         * The stat that is displayed to the client
         * <br>Possible options currently:
         * <ul>
         *     <li>ATK (Base)</li>
         *     <li>CRIT Rate%</li>
         *     <li>CRIT DMG%</li>
         *     <li>HP%</li>
         *     <li>Energy Recharge%</li>
         *     <li>Elemental Mastery</li>
         *     <li>DEF%</li>
         *     <li>ATK%</li>
         *     <li>Physical DMG Bonus%</li>
         * </ul>
         */
        private final String clientName;
        /**
         * The value of the weapon stat
         */
        private final double value;
    }
}
