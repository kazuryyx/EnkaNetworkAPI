package me.kazury.enkanetworkapi.games.genshin.data;

import me.kazury.enkanetworkapi.games.genshin.util.NumberHelper;
import me.kazury.enkanetworkapi.util.IValueAcceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a table with all the possible properties that a character can have.
 * <br>Each property has a unique ID that is used to identify it.
 * <br><a href="https://api.enka.network/#/api?id=fightprop">alternate list</a>
 */
public enum GenshinFightProp {
    // Base Stats
    BASE_HP("1", "Base HP", NumberHelper::format, ValueType.FLAT),
    BASE_ATTACK("4", "Base ATK", NumberHelper::format, ValueType.FLAT),
    BASE_DEFENSE("7", "Base DEF", NumberHelper::format, ValueType.FLAT),
    BASE_SPEED("10", "Base SPD", NumberHelper::format, ValueType.FLAT),

    // Percentage Stats
    HP_PERCENTAGE("3", "HP%", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ATTACK_PERCENTAGE("6", "ATK%", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    DEFENSE_PERCENTAGE("9", "DEF%", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    SPEED_PERCENTAGE("11", "SPD%", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    // Flat Bonuses
    HP("2", "HP", NumberHelper::format, ValueType.FLAT),
    ATTACK("5", "ATK", NumberHelper::format, ValueType.FLAT),
    DEFENSE("8", "DEF", NumberHelper::format, ValueType.FLAT),

    // Crit Bonuses
    CRITICAL_RATE("20", "CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CRITICAL_DAMAGE("22", "CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    // Misc Bonuses
    ENERGY_RECHARGE("23", "Energy Recharge", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    HEALING_BONUS("26", "Healing Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    INCOMING_HEALING_BONUS("27", "Incoming Healing Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ELEMENTAL_MASTERY("28", "Elemental Mastery", NumberHelper::format, ValueType.FLAT),
    COOLDOWN_REDUCTION("80", "Cooldown reduction", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    SHIELD_STRENGTH("81", "Shield Strength", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CURRENT_HP("1010", "Current HP", NumberHelper::format, ValueType.FLAT),
    MAX_HP("2000", "Max HP", NumberHelper::format, ValueType.FLAT),

    // Damage Bonuses
    PHYSICAL_DAMAGE_BONUS("30", "Physical DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    PYRO_DAMAGE_BONUS("40", "Pyro DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ELECTRO_DAMAGE_BONUS("41", "Electro DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    HYDRO_DAMAGE_BONUS("42", "Hydro DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    DENDRO_DAMAGE_BONUS("43", "Dendro DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ANEMO_DAMAGE_BONUS("44", "Anemo DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    GEO_DAMAGE_BONUS("45", "Geo DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CRYO_DAMAGE_BONUS("46", "Cryo DMG Bonus", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    // Resistances
    PHYSICAL_DAMAGE_RESISTANCE("29", "Physical RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    PYRO_RESISTANCE("50", "Pyro RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ELECTRO_RESISTANCE("51", "Electro RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    HYDRO_RESISTANCE("52", "Hydro RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    DENDRO_RESISTANCE("53", "Dendro RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ANEMO_RESISTANCE("54", "Anemo RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    GEO_RESISTANCE("55", "Geo RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CRYO_RESISTANCE("56", "Cryo RES", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    // Energy Costs
    PYRO_ENERGY_COST("70", "Pyro Energy Cost", NumberHelper::format, ValueType.FLAT),
    ELECTRO_ENERGY_COST("71", "Electro Energy Cost", NumberHelper::format, ValueType.FLAT),
    HYDRO_ENERGY_COST("72", "Hydro Energy Cost", NumberHelper::format, ValueType.FLAT),
    DENDRO_ENERGY_COST("73", "Dendro Energy Cost", NumberHelper::format, ValueType.FLAT),
    ANEMO_ENERGY_COST("74", "Anemo Energy Cost", NumberHelper::format, ValueType.FLAT),
    CRYO_ENERGY_COST("75", "Cryo Energy Cost", NumberHelper::format, ValueType.FLAT),
    GEO_ENERGY_COST("76", "Geo Energy Cost", NumberHelper::format, ValueType.FLAT),

    // Current Energy
    CURRENT_PYRO_ENERGY("1000", "Current Pyro Energy", NumberHelper::format, ValueType.FLAT),
    CURRENT_ELECTRO_ENERGY("1001", "Current Electro Energy", NumberHelper::format, ValueType.FLAT),
    CURRENT_HYDRO_ENERGY("1002", "Current Hydro Energy", NumberHelper::format, ValueType.FLAT),
    CURRENT_DENDRO_ENERGY("1003", "Current Dendro Energy", NumberHelper::format, ValueType.FLAT),
    CURRENT_ANEMO_ENERGY("1004", "Current Anemo Energy", NumberHelper::format, ValueType.FLAT),
    CURRENT_CRYO_ENERGY("1005", "Current Cryo Energy", NumberHelper::format, ValueType.FLAT),
    CURRENT_GEO_ENERGY("1006", "Current Geo Energy", NumberHelper::format, ValueType.FLAT),

    CURRENT_ATK("2001", "ATK", NumberHelper::format, ValueType.FLAT),
    CURRENT_DEF("2002", "DEF", NumberHelper::format, ValueType.FLAT),
    /**
     * please I don't know what this is
     */
    IDK_SPD("2003", "SPD", NumberHelper::none, ValueType.UNKNOWN),

    // Elemental Reactions
    REACTION_CRIT_RATE("3025", "Elemental reaction CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    REACTION_CRIT_DAMAGE("3026", "Elemental reaction CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    OVERLOADED_CRIT_RATE("3027", "(Overloaded) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    OVERLOADED_CRIT_DAMAGE("3028", "(Overloaded) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    SWIRL_CRIT_RATE("3029", "(Swirl) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    SWIRL_CRIT_DAMAGE("3030", "(Swirl) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    ELECTROCHARGED_CRIT_RATE("3031", "(Electro-Charged) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ELECTROCHARGED_CRIT_DAMAGE("3032", "(Electro-Charged) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    SUPERCONDUCT_CRIT_RATE("3033", "(Superconduct) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    SUPERCONDUCT_CRIT_DAMAGE("3034", "(Superconduct) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    BURNING_CRIT_RATE("3035", "(Burning) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BURNING_CRIT_DAMAGE("3036", "(Burning) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    FROZEN_SHATTERED_CRIT_RATE("3037", "(Frozen/Shattered) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    FROZEN_SHATTERED_CRIT_DAMAGE("3038", "(Frozen/Shattered) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    BLOOM_CRIT_RATE("3039", "(Bloom) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BLOOM_CRIT_DAMAGE("3040", "(Bloom) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    BURGEON_CRIT_RATE("3041", "(Burgeon) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BURGEON_CRIT_DAMAGE("3042", "(Burgeon) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    HYPERBLOOM_CRIT_RATE("3043", "(Hyperbloom) CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    HYPERBLOOM_CRIT_DAMAGE("3044", "(Hyperbloom) CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE),

    REACTION_BASE_RATE("3045", "Base Elemental reaction CRIT Rate", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    REACTION_BASE_DAMAGE("3046", "Base Elemental reaction CRIT DMG", NumberHelper::formatPercentage, ValueType.PERCENTAGE);

    @NotNull
    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final IValueAcceptor acceptor;

    @NotNull
    private final ValueType valueType;

    GenshinFightProp(@NotNull String id,
                     @NotNull String name,
                     @NotNull IValueAcceptor acceptor,
                     @NotNull ValueType valueType) {
        this.id = id;
        this.name = name;
        this.acceptor = acceptor;
        this.valueType = valueType;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public IValueAcceptor getAcceptor() {
        return this.acceptor;
    }

    @NotNull
    public ValueType getValueType() {
        return this.valueType;
    }

    @Nullable
    public static GenshinFightProp fromKey(@NotNull String key) {
        for (GenshinFightProp fightProp : values()) {
            if (!fightProp.getId().equals(key)) continue;
            return fightProp;
        }
        return null;
    }

    public enum ValueType {
        FLAT,
        PERCENTAGE,
        UNKNOWN
    }
}
