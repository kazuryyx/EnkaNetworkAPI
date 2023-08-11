package me.kazury.enkanetworkapi.genshin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.util.IValueAcceptor;
import me.kazury.enkanetworkapi.genshin.util.NumberHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An append prop.
 * <br>This is everything that is shown when pressing "View Details" in a character menu.
 */
@AllArgsConstructor
@Getter
public enum GenshinAppendProp {
    // WEAPON BASE ATK
    FIGHT_PROP_BASE_ATTACK("ATK", NumberHelper::format),

    FIGHT_PROP_HP("HP", NumberHelper::format),
    FIGHT_PROP_ATTACK("ATK", NumberHelper::format),
    FIGHT_PROP_DEFENSE("DEF", NumberHelper::format),
    FIGHT_PROP_HP_PERCENT("HP", NumberHelper::formatPercentage),
    FIGHT_PROP_ATTACK_PERCENT("ATK", NumberHelper::formatPercentage),
    FIGHT_PROP_DEFENSE_PERCENT("DEF", NumberHelper::formatPercentage),
    FIGHT_PROP_CRITICAL("CRIT Rate", NumberHelper::formatPercentage),
    FIGHT_PROP_CRITICAL_HURT("CRIT DMG", NumberHelper::formatPercentage),
    FIGHT_PROP_CHARGE_EFFICIENCY("Energy Recharge", NumberHelper::formatPercentage),
    FIGHT_PROP_HEAL_ADD("Healing Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_ELEMENT_MASTERY("Elemental Mastery", NumberHelper::format),
    FIGHT_PROP_PHYSICAL_ADD_HURT("Physical DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_FIRE_ADD_HURT("Pyro DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_ELEC_ADD_HURT("Electro DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_WATER_ADD_HURT("Hydro DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_WIND_ADD_HURT("Anemo DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_ICE_ADD_HURT("Cryo DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_ROCK_ADD_HURT("Geo DMG Bonus", NumberHelper::formatPercentage),
    FIGHT_PROP_GRASS_ADD_HURT("Dendro DMG Bonus", NumberHelper::formatPercentage);

    @NotNull
    private final String id;

    @NotNull
    private final IValueAcceptor acceptor;

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
