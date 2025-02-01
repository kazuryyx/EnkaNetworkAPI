package me.kazury.enkanetworkapi.games.starrail.data;

import me.kazury.enkanetworkapi.games.genshin.util.NumberHelper;
import me.kazury.enkanetworkapi.util.IValueAcceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An append prop for Honkai: Star Rail.
 * <br>This represents a stat that can be from a weapon or from a relic / trace.
 */
public enum SRAppendProp {
    MAX_HP("MaxHP", NumberHelper::format, ValueType.FLAT),
    ATTACK("Attack", NumberHelper::format, ValueType.FLAT),
    DEFENCE("Defence", NumberHelper::format, ValueType.FLAT),
    SPEED("Speed", NumberHelper::format, ValueType.FLAT),
    CRITICAL_CHANCE("CriticalChance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CRITICAL_DAMAGE("CriticalDamage", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BREAK_DAMAGE_ADDED_RATIO("BreakDamageAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BREAK_DAMAGE_ADDED_RATIO_BASE("BreakDamageAddedRatioBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    HEAL_RATIO("HealRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    MAX_SP("MaxSP", NumberHelper::format, ValueType.FLAT),
    SP_RATIO("SPRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    STATUS_PROBABILTY("StatusProbability", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    STATUS_RESISTANCE("StatusResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CRITICAL_CHANCE_BASE("CriticalChanceBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    CRITICAL_DAMAGE_BASE("CriticalDamageBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    HEAL_RATIO_BASE("HealRatioBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    STANCE_BREAK_ADDED_RATIO("StanceBreakAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    SP_RATIO_BASE("SPRatioBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    STATUS_PROBABILTY_BASE("StatusProbabiltyBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    STATUS_RESISTANCE_BASE("StatusResistanceBase", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    PHYSICAL_ADDED_RATIO("PhysicalAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    PHYSICAL_RESISTANCE("PhysicalResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    FIRE_ADDED_RATIO("FireAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    FIRE_RESISTANCE("FireResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ICE_ADDED_RATIO("IceAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ICE_RESISTANCE("IceResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    THUNDER_ADDED_RATIO("ThunderAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    THUNDER_RESISTANCE("ThunderResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    WIND_ADDED_RATIO("WindAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    WIND_RESISTANCE("WindResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    QUANTUM_ADDED_RATIO("QuantumAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    QUANTUM_RESISTANCE("QuantumResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    IMAGINARY_ADDED_RATIO("ImaginaryAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    IMAGINARY_RESISTANCE("ImaginaryResistance", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BASE_HP("BaseHP", NumberHelper::format, ValueType.FLAT),
    HP_DELTA("HPDelta", NumberHelper::format, ValueType.FLAT),
    HP_ADDED_RATIO("HPAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BASE_ATTACK("BaseAttack", NumberHelper::format, ValueType.FLAT),
    ATTACK_DELTA("AttackDelta", NumberHelper::format, ValueType.FLAT),
    ATTACK_ADDED_RATIO("AttackAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BASE_DEFENCE("BaseDefence", NumberHelper::format, ValueType.FLAT),
    DEFENCE_DELTA("DefenceDelta", NumberHelper::format, ValueType.FLAT),
    DEFENCE_ADDED_RATIO("DefenceAddedRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    BASE_SPEED("BaseSpeed", NumberHelper::format, ValueType.FLAT),
    HEAL_TAKEN_RATIO("HealTakenRatio", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    PHSYSICAL_RESISTANCE_DELTA("PhsysicalResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    FIRE_RESISTANCE_DELTA("FireResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    ICE_RESISTANCE_DELTA("IceResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    THUNDER_RESISTANCE_DELTA("ThunderResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    WIND_RESISTANCE_DELTA("WindResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    QUANTUM_RESISTANCE_DELTA("QuantumResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    IMAGINARY_RESISTANCE_DELTA("ImaginaryResistanceDelta", NumberHelper::formatPercentage, ValueType.PERCENTAGE),
    SPEED_DELTA("SpeedDelta", NumberHelper::format, ValueType.FLAT),
    ;

    @NotNull
    private final String key;

    @NotNull
    private final IValueAcceptor acceptor;

    @NotNull
    private final ValueType valueType;

    SRAppendProp(@NotNull String key, @NotNull IValueAcceptor acceptor, @NotNull ValueType valueType) {
        this.key = key;
        this.acceptor = acceptor;
        this.valueType = valueType;
    }

    @NotNull
    public String getKey() {
        return this.key;
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
    public static SRAppendProp fromKey(@NotNull String key) {
        for (SRAppendProp prop : values()) {
            if (prop.getKey().equalsIgnoreCase(key)) {
                return prop;
            }
        }
        return null;
    }

    public enum ValueType {
        FLAT,
        PERCENTAGE
    }
}
