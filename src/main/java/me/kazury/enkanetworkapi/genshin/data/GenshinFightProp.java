package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
    BASE_HP("1", "Base HP"),
    BASE_ATTACK("4", "Base ATK"),
    BASE_DEFENSE("7", "Base DEF"),
    BASE_SPEED("10", "Base SPD"),

    // Percentage Stats
    HP_PERCENTAGE("3", "HP%"),
    ATTACK_PERCENTAGE("6", "ATK%"),
    DEFENSE_PERCENTAGE("9", "DEF%"),
    SPEED_PERCENTAGE("11", "SPD%"),

    // Flat Bonuses
    HP("2", "HP"),
    ATTACK("5", "ATK"),
    DEFENSE("8", "DEF"),

    // Crit Bonuses
    CRITICAL_RATE("20", "CRIT Rate"),
    CRITICAL_DAMAGE("22", "CRIT DMG"),

    // Misc Bonuses
    ENERGY_RECHARGE("23", "Energy Recharge"),
    HEALING_BONUS("26", "Healing Bonus"),
    INCOMING_HEALING_BONUS("27", "Incoming Healing Bonus"),
    ELEMENTAL_MASTERY("28", "Elemental Mastery"),
    COOLDOWN_REDUCTION("80", "Cooldown reduction"),
    SHIELD_STRENGTH("81", "Shield Strength"),
    CURRENT_HP("1010", "Current HP"),
    MAX_HP("2000", "Max HP"),

    // Damage Bonuses
    PHYSICAL_DAMAGE_BONUS("30", "Physical DMG Bonus"),
    PYRO_DAMAGE_BONUS("40", "Pyro DMG Bonus"),
    ELECTRO_DAMAGE_BONUS("41", "Electro DMG Bonus"),
    HYDRO_DAMAGE_BONUS("42", "Hydro DMG Bonus"),
    DENDRO_DAMAGE_BONUS("43", "Dendro DMG Bonus"),
    ANEMO_DAMAGE_BONUS("44", "Anemo DMG Bonus"),
    GEO_DAMAGE_BONUS("45", "Geo DMG Bonus"),
    CRYO_DAMAGE_BONUS("46", "Cryo DMG Bonus"),

    // Resistances
    PHYSICAL_DAMAGE_RESISTANCE("29", "Physical RES"),
    PYRO_RESISTANCE("50", "Pyro RES"),
    ELECTRO_RESISTANCE("51", "Electro RES"),
    HYDRO_RESISTANCE("52", "Hydro RES"),
    DENDRO_RESISTANCE("53", "Dendro RES"),
    ANEMO_RESISTANCE("54", "Anemo RES"),
    GEO_RESISTANCE("55", "Geo RES"),
    CRYO_RESISTANCE("56", "Cryo RES"),

    // Energy Costs
    PYRO_ENERGY_COST("70", "Pyro Energy Cost"),
    ELECTRO_ENERGY_COST("71", "Electro Energy Cost"),
    HYDRO_ENERGY_COST("72", "Hydro Energy Cost"),
    DENDRO_ENERGY_COST("73", "Dendro Energy Cost"),
    ANEMO_ENERGY_COST("74", "Anemo Energy Cost"),
    CRYO_ENERGY_COST("75", "Cryo Energy Cost"),
    GEO_ENERGY_COST("76", "Geo Energy Cost"),

    // Current Energy
    CURRENT_PYRO_ENERGY("1000", "Current Pyro Energy"),
    CURRENT_ELECTRO_ENERGY("1001", "Current Electro Energy"),
    CURRENT_HYDRO_ENERGY("1002", "Current Hydro Energy"),
    CURRENT_DENDRO_ENERGY("1003", "Current Dendro Energy"),
    CURRENT_ANEMO_ENERGY("1004", "Current Anemo Energy"),
    CURRENT_CRYO_ENERGY("1005", "Current Cryo Energy"),
    CURRENT_GEO_ENERGY("1006", "Current Geo Energy"),

    IDK_ATK("2001", "ATK"),
    IDK_DEF("2002", "DEF"),
    IDK_SPD("2003", "SPD"),

    // Elemental Reactions
    REACTION_CRIT_RATE("3025", "Elemental reaction CRIT Rate"),
    REACTION_CRIT_DAMAGE("3026", "Elemental reaction CRIT DMG"),

    OVERLOADED_CRIT_RATE("3027", "(Overloaded) CRIT Rate"),
    OVERLOADED_CRIT_DAMAGE("3028", "(Overloaded) CRIT DMG"),

    SWIRL_CRIT_RATE("3029", "(Swirl) CRIT Rate"),
    SWIRL_CRIT_DAMAGE("3030", "(Swirl) CRIT DMG"),

    ELECTROCHARGED_CRIT_RATE("3031", "(Electro-Charged) CRIT Rate"),
    ELECTROCHARGED_CRIT_DAMAGE("3032", "(Electro-Charged) CRIT DMG"),

    SUPERCONDUCT_CRIT_RATE("3033", "(Superconduct) CRIT Rate"),
    SUPERCONDUCT_CRIT_DAMAGE("3034", "(Superconduct) CRIT DMG"),

    BURNING_CRIT_RATE("3035", "(Burning) CRIT Rate"),
    BURNING_CRIT_DAMAGE("3036", "(Burning) CRIT DMG"),

    FROZEN_SHATTERED_CRIT_RATE("3037", "(Frozen/Shattered) CRIT Rate"),
    FROZEN_SHATTERED_CRIT_DAMAGE("3038", "(Frozen/Shattered) CRIT DMG"),

    BLOOM_CRIT_RATE("3039", "(Bloom) CRIT Rate"),
    BLOOM_CRIT_DAMAGE("3040", "(Bloom) CRIT DMG"),

    BURGEON_CRIT_RATE("3041", "(Burgeon) CRIT Rate"),
    BURGEON_CRIT_DAMAGE("3042", "(Burgeon) CRIT DMG"),

    HYPERBLOOM_CRIT_RATE("3043", "(Hyperbloom) CRIT Rate"),
    HYPERBLOOM_CRIT_DAMAGE("3044", "(Hyperbloom) CRIT DMG"),

    REACTION_BASE_RATE("3045", "Base Elemental reaction CRIT Rate"),
    REACTION_BASE_DAMAGE("3046", "Base Elemental reaction CRIT DMG");

    @NotNull
    private final String id;

    @NotNull
    private final String name;

    @Nullable
    public static GenshinFightProp fromKey(@NotNull String key) {
        for (GenshinFightProp fightProp : values()) {
            if (fightProp.getId().equals(key)) {
                return fightProp;
            }
        }
        return null;
    }
}
