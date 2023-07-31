package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Getter
public enum GenshinAppendProp {
    FIGHT_PROP_BASE_ATTACK("Base ATK"), // WEAPON ONLY
    FIGHT_PROP_HP("HP"),
    FIGHT_PROP_ATTACK("ATK"),
    FIGHT_PROP_DEFENSE("DEF"),
    FIGHT_PROP_HP_PERCENT("HP%"),
    FIGHT_PROP_ATTACK_PERCENT("ATK%"),
    FIGHT_PROP_DEFENSE_PERCENT("DEF%"),
    FIGHT_PROP_CRITICAL("CRIT Rate"),
    FIGHT_PROP_CRITICAL_HURT("CRIT DMG"),
    FIGHT_PROP_CHARGE_EFFICIENCY("Energy Recharge"),
    FIGHT_PROP_HEAL_ADD("Healing Bonus"),
    FIGHT_PROP_ELEMENT_MASTERY("Elemental Mastery"),
    FIGHT_PROP_PHYSICAL_ADD_HURT("Physical DMG Bonus"),
    FIGHT_PROP_FIRE_ADD_HURT("Pyro DMG Bonus"),
    FIGHT_PROP_ELEC_ADD_HURT("Electro DMG Bonus"),
    FIGHT_PROP_WATER_ADD_HURT("Hydro DMG Bonus"),
    FIGHT_PROP_WIND_ADD_HURT("Anemo DMG Bonus"),
    FIGHT_PROP_ICE_ADD_HURT("Cryo DMG Bonus"),
    FIGHT_PROP_ROCK_ADD_HURT("Geo DMG Bonus"),
    FIGHT_PROP_GRASS_ADD_HURT("Dendro DMG Bonus");

    @NotNull
    private final String id;

    @Nullable
    public static GenshinAppendProp fromKey(@NotNull String key) {
        for (GenshinAppendProp fightProp : values()) {
            if (fightProp.name().equals(key)) {
                return fightProp;
            }
        }
        return null;
    }
}
