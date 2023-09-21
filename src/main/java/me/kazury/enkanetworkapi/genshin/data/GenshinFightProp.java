package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kazury.enkanetworkapi.util.IValueAcceptor;
import me.kazury.enkanetworkapi.genshin.util.NumberHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a table with all the possible properties that a character can have.
 * <br>Each property has a unique ID that is used to identify it.
 * <br><a href="https://api.enka.network/#/api?id=fightprop">alternate list</a>
 */
@Getter
@AllArgsConstructor
public enum GenshinFightProp {
    // Base Stats
    BASE_HP("1", "Base HP", NumberHelper::format),
    BASE_ATTACK("4", "Base ATK", NumberHelper::format),
    BASE_DEFENSE("7", "Base DEF", NumberHelper::format),
    BASE_SPEED("10", "Base SPD", NumberHelper::format),

    // Percentage Stats
    HP_PERCENTAGE("3", "HP%", NumberHelper::formatPercentage),
    ATTACK_PERCENTAGE("6", "ATK%", NumberHelper::formatPercentage),
    DEFENSE_PERCENTAGE("9", "DEF%", NumberHelper::formatPercentage),
    SPEED_PERCENTAGE("11", "SPD%", NumberHelper::formatPercentage),

    // Flat Bonuses
    HP("2", "HP", NumberHelper::format),
    ATTACK("5", "ATK", NumberHelper::format),
    DEFENSE("8", "DEF", NumberHelper::format),

    // Crit Bonuses
    CRITICAL_RATE("20", "CRIT Rate", NumberHelper::formatPercentage),
    CRITICAL_DAMAGE("22", "CRIT DMG", NumberHelper::formatPercentage),

    // Misc Bonuses
    ENERGY_RECHARGE("23", "Energy Recharge", NumberHelper::formatPercentage),
    HEALING_BONUS("26", "Healing Bonus", NumberHelper::formatPercentage),
    INCOMING_HEALING_BONUS("27", "Incoming Healing Bonus", NumberHelper::formatPercentage),
    ELEMENTAL_MASTERY("28", "Elemental Mastery", NumberHelper::format),
    COOLDOWN_REDUCTION("80", "Cooldown reduction", NumberHelper::formatPercentage),
    SHIELD_STRENGTH("81", "Shield Strength", NumberHelper::formatPercentage),
    CURRENT_HP("1010", "Current HP", NumberHelper::format),
    MAX_HP("2000", "Max HP", NumberHelper::format),

    // Damage Bonuses
    PHYSICAL_DAMAGE_BONUS("30", "Physical DMG Bonus", NumberHelper::formatPercentage),
    PYRO_DAMAGE_BONUS("40", "Pyro DMG Bonus", NumberHelper::formatPercentage),
    ELECTRO_DAMAGE_BONUS("41", "Electro DMG Bonus", NumberHelper::formatPercentage),
    HYDRO_DAMAGE_BONUS("42", "Hydro DMG Bonus", NumberHelper::formatPercentage),
    DENDRO_DAMAGE_BONUS("43", "Dendro DMG Bonus", NumberHelper::formatPercentage),
    ANEMO_DAMAGE_BONUS("44", "Anemo DMG Bonus", NumberHelper::formatPercentage),
    GEO_DAMAGE_BONUS("45", "Geo DMG Bonus", NumberHelper::formatPercentage),
    CRYO_DAMAGE_BONUS("46", "Cryo DMG Bonus", NumberHelper::formatPercentage),

    // Resistances
    PHYSICAL_DAMAGE_RESISTANCE("29", "Physical RES", NumberHelper::formatPercentage),
    PYRO_RESISTANCE("50", "Pyro RES", NumberHelper::formatPercentage),
    ELECTRO_RESISTANCE("51", "Electro RES", NumberHelper::formatPercentage),
    HYDRO_RESISTANCE("52", "Hydro RES", NumberHelper::formatPercentage),
    DENDRO_RESISTANCE("53", "Dendro RES", NumberHelper::formatPercentage),
    ANEMO_RESISTANCE("54", "Anemo RES", NumberHelper::formatPercentage),
    GEO_RESISTANCE("55", "Geo RES", NumberHelper::formatPercentage),
    CRYO_RESISTANCE("56", "Cryo RES", NumberHelper::formatPercentage),

    // Energy Costs
    PYRO_ENERGY_COST("70", "Pyro Energy Cost", NumberHelper::format),
    ELECTRO_ENERGY_COST("71", "Electro Energy Cost", NumberHelper::format),
    HYDRO_ENERGY_COST("72", "Hydro Energy Cost", NumberHelper::format),
    DENDRO_ENERGY_COST("73", "Dendro Energy Cost", NumberHelper::format),
    ANEMO_ENERGY_COST("74", "Anemo Energy Cost", NumberHelper::format),
    CRYO_ENERGY_COST("75", "Cryo Energy Cost", NumberHelper::format),
    GEO_ENERGY_COST("76", "Geo Energy Cost", NumberHelper::format),

    // Current Energy
    CURRENT_PYRO_ENERGY("1000", "Current Pyro Energy", NumberHelper::format),
    CURRENT_ELECTRO_ENERGY("1001", "Current Electro Energy", NumberHelper::format),
    CURRENT_HYDRO_ENERGY("1002", "Current Hydro Energy", NumberHelper::format),
    CURRENT_DENDRO_ENERGY("1003", "Current Dendro Energy", NumberHelper::format),
    CURRENT_ANEMO_ENERGY("1004", "Current Anemo Energy", NumberHelper::format),
    CURRENT_CRYO_ENERGY("1005", "Current Cryo Energy", NumberHelper::format),
    CURRENT_GEO_ENERGY("1006", "Current Geo Energy", NumberHelper::format),

    /**
     * please I don't know what this is
     */
    IDK_ATK("2001", "ATK", NumberHelper::none),
    IDK_DEF("2002", "DEF", NumberHelper::none),
    IDK_SPD("2003", "SPD", NumberHelper::none),

    // Elemental Reactions
    REACTION_CRIT_RATE("3025", "Elemental reaction CRIT Rate", NumberHelper::formatPercentage),
    REACTION_CRIT_DAMAGE("3026", "Elemental reaction CRIT DMG", NumberHelper::formatPercentage),

    OVERLOADED_CRIT_RATE("3027", "(Overloaded) CRIT Rate", NumberHelper::formatPercentage),
    OVERLOADED_CRIT_DAMAGE("3028", "(Overloaded) CRIT DMG", NumberHelper::formatPercentage),

    SWIRL_CRIT_RATE("3029", "(Swirl) CRIT Rate", NumberHelper::formatPercentage),
    SWIRL_CRIT_DAMAGE("3030", "(Swirl) CRIT DMG", NumberHelper::formatPercentage),

    ELECTROCHARGED_CRIT_RATE("3031", "(Electro-Charged) CRIT Rate", NumberHelper::formatPercentage),
    ELECTROCHARGED_CRIT_DAMAGE("3032", "(Electro-Charged) CRIT DMG", NumberHelper::formatPercentage),

    SUPERCONDUCT_CRIT_RATE("3033", "(Superconduct) CRIT Rate", NumberHelper::formatPercentage),
    SUPERCONDUCT_CRIT_DAMAGE("3034", "(Superconduct) CRIT DMG", NumberHelper::formatPercentage),

    BURNING_CRIT_RATE("3035", "(Burning) CRIT Rate", NumberHelper::formatPercentage),
    BURNING_CRIT_DAMAGE("3036", "(Burning) CRIT DMG", NumberHelper::formatPercentage),

    FROZEN_SHATTERED_CRIT_RATE("3037", "(Frozen/Shattered) CRIT Rate", NumberHelper::formatPercentage),
    FROZEN_SHATTERED_CRIT_DAMAGE("3038", "(Frozen/Shattered) CRIT DMG", NumberHelper::formatPercentage),

    BLOOM_CRIT_RATE("3039", "(Bloom) CRIT Rate", NumberHelper::formatPercentage),
    BLOOM_CRIT_DAMAGE("3040", "(Bloom) CRIT DMG", NumberHelper::formatPercentage),

    BURGEON_CRIT_RATE("3041", "(Burgeon) CRIT Rate", NumberHelper::formatPercentage),
    BURGEON_CRIT_DAMAGE("3042", "(Burgeon) CRIT DMG", NumberHelper::formatPercentage),

    HYPERBLOOM_CRIT_RATE("3043", "(Hyperbloom) CRIT Rate", NumberHelper::formatPercentage),
    HYPERBLOOM_CRIT_DAMAGE("3044", "(Hyperbloom) CRIT DMG", NumberHelper::formatPercentage),

    REACTION_BASE_RATE("3045", "Base Elemental reaction CRIT Rate", NumberHelper::formatPercentage),
    REACTION_BASE_DAMAGE("3046", "Base Elemental reaction CRIT DMG", NumberHelper::formatPercentage);

    @NotNull
    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final IValueAcceptor acceptor;

    @Nullable
    public static GenshinFightProp fromKey(@NotNull String key) {
        for (GenshinFightProp fightProp : values()) {
            if (!fightProp.getId().equals(key)) continue;
            return fightProp;
        }
        return null;
    }
}
