package me.kazury.enkanetworkapi.games.zzz.data;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a weapon that is equipped on a character. (Agent)
 * <br>Note that the character may not have a weapon equipped.
 * <br>Then the underlying method will return null.
 */
public class ZZZCharacterWeapon {
    private final boolean available;
    private final boolean locked;
    private final int id;
    private final int uid;
    private final int level;
    private final int breakLevel;
    private final int breakExp;
    private final int upgradeLevel;

    public ZZZCharacterWeapon(final boolean available,
                              final boolean locked,
                              final int id,
                              final int uid,
                              final int level,
                              final int breakLevel,
                              final int breakExp,
                              final int upgradeLevel
    ) {
        this.available = available;
        this.locked = locked;
        this.id = id;
        this.uid = uid;
        this.level = level;
        this.breakLevel = breakLevel;
        this.breakExp = breakExp;
        this.upgradeLevel = upgradeLevel;
    }

    /**
     * Returns if this weapon is available.
     * <br>Idk what this means, I don't play this game.
     */
    public boolean isAvailable() {
        return this.available;
    }

    /**
     * Returns if this weapon is locked.
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Returns the Id of this weapon. (W-Engine)
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the Unique Id of this weapon. (W-Engine)
     * @apiNote UID for weapons and discs are unique to that specific item in a game account. They will persist across upgrades to the disc/weapon and can be used to deduplicate them across multiple queries if you want to keep track of all equipment on the account that was seen in the showcase.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * The level of this weapon. (W-Engine)
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Returns the break level of this weapon. (W-Engine)
     * <br>In Genshin Terms: Ascension
     */
    public int getBreakLevel() {
        return this.breakLevel;
    }

    /**
     * Returns the experience of this weapon.
     * <br>Idk what this means, I don't play this game.
     */
    public int getBreakExp() {
        return this.breakExp;
    }

    /**
     * Returns the upgrade level of this weapon. (W-Engine)
     * <br>In Genshin Terms: Refinement
     */
    public int getUpgradeLevel() {
        return this.upgradeLevel;
    }

    /**
     * Gets the main stat value of this weapon.
     * <br>Formula: MainStat.BaseValue * (1 + 0.1568166666666667 * Level + 0.8922 * BreakLevel)
     */
    public double getMainStatValue() {
        final ZZZWeapon gameData = this.getGameData();
        return gameData.getMainStat().getPropertyValue() * (1 + 0.1568166666666667 * this.level + 0.8922 * this.breakLevel);
    }

    /**
     * Gets the secondary value of this weapon.
     * <br>Formula: SubStat.BaseValue * (1 + 0.3 * BreakLevel)
     */
    public double getSecondaryValue() {
        final ZZZWeapon gameData = this.getGameData();
        return gameData.getSecondaryStat().getPropertyValue() * (1 + 0.3 * this.breakLevel);
    }

    /**
     * Gets the game data of this weapon.
     * <br>This can be used to get the image path, rarity, profession type, main stat, and secondary stat.
     */
    @NotNull
    public ZZZWeapon getGameData() {
        final ZZZWeapon weapon = EnkaCaches.getZenlessWeaponById(String.valueOf(this.id));
        if (weapon == null) throw new UpdateLibraryException();
        return weapon;
    }
}
