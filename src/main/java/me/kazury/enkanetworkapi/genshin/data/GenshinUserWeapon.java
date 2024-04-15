package me.kazury.enkanetworkapi.genshin.data;

import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.util.IFormattable;
import me.kazury.enkanetworkapi.genshin.util.GenshinNameable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A weapon that is currently equipped on a {@link GenshinUserCharacter}
 */
public class GenshinUserWeapon implements GenshinNameable {
    @NotNull
    private final String nameTextMapHash;
    private final int weaponLevel;
    private final int weaponAscension;
    private final int weaponRefinement;
    private final String weaponIcon;

    @NotNull
    private final List<WeaponStat> stats;
    private final int star;
    private final long id;

    public GenshinUserWeapon(@NotNull String nameTextMapHash,
                             final int weaponLevel,
                             final int weaponAscension,
                             final int weaponRefinement,
                             @NotNull String weaponIcon,
                             @NotNull List<WeaponStat> stats,
                             final int star,
                             final long id) {
        this.nameTextMapHash = nameTextMapHash;
        this.weaponLevel = weaponLevel;
        this.weaponAscension = weaponAscension;
        this.weaponRefinement = weaponRefinement;
        this.weaponIcon = weaponIcon;
        this.stats = stats;
        this.star = star;
        this.id = id;
    }

    /**
     * The level of this weapon, this is a number between 1 and 90
     */
    public int getWeaponLevel() {
        return this.weaponLevel;
    }

    /**
     * The current ascension of this weapon, this is a number between 0 and 6
     */
    public int getWeaponAscension() {
        return this.weaponAscension;
    }

    /**
     * The current refinement of this weapon, this is a number between 1 and 5
     * <br>To get refinements on weapons, you need to get a copy of the weapon.
     */
    public int getWeaponRefinement() {
        return this.weaponRefinement;
    }

    /**
     * The icon ID of the weapon
     * <br>You will have to parse this yourself with {@link EnkaNetworkAPI#getGenshinIcon(String)}
     */
    @NotNull
    public String getWeaponIcon() {
        return this.weaponIcon;
    }

    /**
     * A weapon can have up to 2 stats
     * <br>One always being the Base ATK, and another one being a different stat (either % stats or flat stats).
     */
    @NotNull
    public List<WeaponStat> getStats() {
        return this.stats;
    }

    /**
     * Gets the rarity of this weapon.
     * <br>For example, Furinas signature weapon is a 5-star weapon, while a Favonius is a 4-star Weapon.
     */
    public int getStar() {
        return this.star;
    }

    /**
     * Gets the id of this weapon
     */
    public long getId() {
        return this.id;
    }

    /**
     * Represents a localization key for the weapon name.
     */
    @Override
    @NotNull
    public String getNameTextMapHash() {
        return this.nameTextMapHash;
    }

    /**
     * @return Builder for weapon
     */
    @NotNull
    public static GenshinUserWeaponBuilder builder() {
        return new GenshinUserWeaponBuilder();
    }

    /**
     * A weapon stat, this is either the main stat, or the second stat.
     */
    public static class WeaponStat implements IFormattable {
        private final String stat;
        private final String formattedValue;
        private final double rawValue;

        public WeaponStat(@NotNull String stat, @NotNull String formattedValue, final double rawValue) {
            this.stat = stat;
            this.formattedValue = formattedValue;
            this.rawValue = rawValue;
        }

        @NotNull
        @Override
        public String getStat() {
            return this.stat;
        }

        @NotNull
        @Override
        public String getFormattedValue() {
            return this.formattedValue;
        }

        @Override
        public double getRawValue() {
            return this.rawValue;
        }
    }

    public static class GenshinUserWeaponBuilder {
        private String nameTextMapHash;
        private int weaponLevel;
        private int weaponAscension;
        private int weaponRefinement;
        private String weaponIcon;
        private List<WeaponStat> stats;
        private int star;
        private long id;

        @NotNull
        public GenshinUserWeaponBuilder nameTextMapHash(@NotNull String nameTextMapHash) {
            this.nameTextMapHash = nameTextMapHash;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder weaponLevel(final int weaponLevel) {
            this.weaponLevel = weaponLevel;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder weaponAscension(final int weaponAscension) {
            this.weaponAscension = weaponAscension;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder weaponRefinement(final int weaponRefinement) {
            this.weaponRefinement = weaponRefinement;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder weaponIcon(@NotNull String weaponIcon) {
            this.weaponIcon = weaponIcon;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder stats(@NotNull List<WeaponStat> stats) {
            this.stats = stats;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder id(final long id) {
            this.id = id;
            return this;
        }

        @NotNull
        public GenshinUserWeaponBuilder star(final int star) {
            this.star = star;
            return this;
        }

        @NotNull
        public GenshinUserWeapon build() {
            return new GenshinUserWeapon(
                    this.nameTextMapHash,
                    this.weaponLevel,
                    this.weaponAscension,
                    this.weaponRefinement,
                    this.weaponIcon,
                    this.stats,
                    this.star,
                    this.id
            );
        }
    }
}
