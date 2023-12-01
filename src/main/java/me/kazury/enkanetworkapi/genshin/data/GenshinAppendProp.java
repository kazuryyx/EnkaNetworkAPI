package me.kazury.enkanetworkapi.genshin.data;

import me.kazury.enkanetworkapi.util.IValueAcceptor;
import me.kazury.enkanetworkapi.genshin.util.NumberHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An append prop.
 * <br>This is everything that is shown when pressing "View Details" in a character menu.
 */
public enum GenshinAppendProp {
    // WEAPON BASE ATK
    FIGHT_PROP_BASE_ATTACK("ATK", NumberHelper::format, GenshinFightProp.ValueType.FLAT),

    FIGHT_PROP_HP("HP", NumberHelper::format, GenshinFightProp.ValueType.FLAT),
    FIGHT_PROP_ATTACK("ATK", NumberHelper::format, GenshinFightProp.ValueType.FLAT),
    FIGHT_PROP_DEFENSE("DEF", NumberHelper::format, GenshinFightProp.ValueType.FLAT),
    FIGHT_PROP_HP_PERCENT("HP", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_ATTACK_PERCENT("ATK", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_DEFENSE_PERCENT("DEF", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_CRITICAL("CRIT Rate", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_CRITICAL_HURT("CRIT DMG", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_CHARGE_EFFICIENCY("Energy Recharge", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_HEAL_ADD("Healing Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_ELEMENT_MASTERY("Elemental Mastery", NumberHelper::format, GenshinFightProp.ValueType.FLAT),
    FIGHT_PROP_PHYSICAL_ADD_HURT("Physical DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_FIRE_ADD_HURT("Pyro DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_ELEC_ADD_HURT("Electro DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_WATER_ADD_HURT("Hydro DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_WIND_ADD_HURT("Anemo DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_ICE_ADD_HURT("Cryo DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_ROCK_ADD_HURT("Geo DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE),
    FIGHT_PROP_GRASS_ADD_HURT("Dendro DMG Bonus", NumberHelper::formatPercentage, GenshinFightProp.ValueType.PERCENTAGE);

    @NotNull
    private final String id;

    @NotNull
    private final IValueAcceptor acceptor;

    @NotNull
    private final GenshinFightProp.ValueType valueType;

    GenshinAppendProp(@NotNull String id, @NotNull IValueAcceptor acceptor,
                      @NotNull GenshinFightProp.ValueType valueType) {
        this.id = id;
        this.acceptor = acceptor;
        this.valueType = valueType;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    @NotNull
    public IValueAcceptor getAcceptor() {
        return this.acceptor;
    }

    @NotNull
    public GenshinFightProp.ValueType getValueType() {
        return this.valueType;
    }

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
