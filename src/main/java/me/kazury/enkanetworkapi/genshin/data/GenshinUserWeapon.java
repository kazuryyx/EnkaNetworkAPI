package me.kazury.enkanetworkapi.genshin.data;

import lombok.Builder;
import lombok.Data;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.genshin.util.INameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A weapon that is currently equipped on a {@link GenshinUserCharacter}
 */
@Data
@Builder
public class GenshinUserWeapon implements INameable {
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

    @Override
    @NotNull
    public String getNameTextMapHash() {
        return this.nameTextMapHash;
    }

    /**
     * A weapon stat, this is either the main stat, or the second stat.
     */
    @Data
    public static class WeaponStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;
    }
}
