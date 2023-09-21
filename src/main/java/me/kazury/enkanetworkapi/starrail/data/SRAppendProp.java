package me.kazury.enkanetworkapi.starrail.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kazury.enkanetworkapi.genshin.util.NumberHelper;
import me.kazury.enkanetworkapi.util.IValueAcceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An append prop for Honkai: Star Rail.
 * <br>This represents a stat that can be from a weapon or from a relic / trace.
 */
@AllArgsConstructor
@Getter
public enum SRAppendProp {
    MAX_HP("MaxHP", NumberHelper::format),
    ATTACK("Attack", NumberHelper::format),
    DEFENCE("Defence", NumberHelper::format),
    SPEED("Speed", NumberHelper::format),
    CRITICAL_CHANCE("CriticalChance", NumberHelper::formatPercentage),
    CRITICAL_DAMAGE("CriticalDamage", NumberHelper::formatPercentage),
    BREAK_DAMAGE_ADDED_RATIO("BreakDamageAddedRatio", NumberHelper::formatPercentage),
    BREAK_DAMAGE_ADDED_RATIO_BASE("BreakDamageAddedRatioBase", NumberHelper::formatPercentage),
    HEAL_RATIO("HealRatio", NumberHelper::formatPercentage),
    MAX_SP("MaxSP", NumberHelper::format),
    SP_RATIO("SPRatio", NumberHelper::formatPercentage),
    STATUS_PROBABILTY("StatusProbabilty", NumberHelper::formatPercentage),
    STATUS_RESISTANCE("StatusResistance", NumberHelper::formatPercentage),
    CRITICAL_CHANCE_BASE("CriticalChanceBase", NumberHelper::formatPercentage),
    CRITICAL_DAMAGE_BASE("CriticalDamageBase", NumberHelper::formatPercentage),
    HEAL_RATIO_BASE("HealRatioBase", NumberHelper::formatPercentage),
    STANCE_BREAK_ADDED_RATIO("StanceBreakAddedRatio", NumberHelper::formatPercentage),
    SP_RATIO_BASE("SPRatioBase", NumberHelper::formatPercentage),
    STATUS_PROBABILTY_BASE("StatusProbabiltyBase", NumberHelper::formatPercentage),
    STATUS_RESISTANCE_BASE("StatusResistanceBase", NumberHelper::formatPercentage),
    PHYSICAL_ADDED_RATIO("PhysicalAddedRatio", NumberHelper::formatPercentage),
    PHYSICAL_RESISTANCE("PhysicalResistance", NumberHelper::formatPercentage),
    FIRE_ADDED_RATIO("FireAddedRatio", NumberHelper::formatPercentage),
    FIRE_RESISTANCE("FireResistance", NumberHelper::formatPercentage),
    ICE_ADDED_RATIO("IceAddedRatio", NumberHelper::formatPercentage),
    ICE_RESISTANCE("IceResistance", NumberHelper::formatPercentage),
    THUNDER_ADDED_RATIO("ThunderAddedRatio", NumberHelper::formatPercentage),
    THUNDER_RESISTANCE("ThunderResistance", NumberHelper::formatPercentage),
    WIND_ADDED_RATIO("WindAddedRatio", NumberHelper::formatPercentage),
    WIND_RESISTANCE("WindResistance", NumberHelper::formatPercentage),
    QUANTUM_ADDED_RATIO("QuantumAddedRatio", NumberHelper::formatPercentage),
    QUANTUM_RESISTANCE("QuantumResistance", NumberHelper::formatPercentage),
    IMAGINARY_ADDED_RATIO("ImaginaryAddedRatio", NumberHelper::formatPercentage),
    IMAGINARY_RESISTANCE("ImaginaryResistance", NumberHelper::formatPercentage),
    BASE_HP("BaseHP", NumberHelper::format),
    HP_DELTA("HPDelta", NumberHelper::format),
    HP_ADDED_RATIO("HPAddedRatio", NumberHelper::formatPercentage),
    BASE_ATTACK("BaseAttack", NumberHelper::format),
    ATTACK_DELTA("AttackDelta", NumberHelper::format),
    ATTACK_ADDED_RATIO("AttackAddedRatio", NumberHelper::formatPercentage),
    BASE_DEFENCE("BaseDefence", NumberHelper::format),
    DEFENCE_DELTA("DefenceDelta", NumberHelper::format),
    DEFENCE_ADDED_RATIO("DefenceAddedRatio", NumberHelper::formatPercentage),
    BASE_SPEED("BaseSpeed", NumberHelper::format),
    HEAL_TAKEN_RATIO("HealTakenRatio", NumberHelper::formatPercentage),
    PHSYSICAL_RESISTANCE_DELTA("PhsysicalResistanceDelta", NumberHelper::formatPercentage),
    FIRE_RESISTANCE_DELTA("FireResistanceDelta", NumberHelper::formatPercentage),
    ICE_RESISTANCE_DELTA("IceResistanceDelta", NumberHelper::formatPercentage),
    THUNDER_RESISTANCE_DELTA("ThunderResistanceDelta", NumberHelper::formatPercentage),
    WIND_RESISTANCE_DELTA("WindResistanceDelta", NumberHelper::formatPercentage),
    QUANTUM_RESISTANCE_DELTA("QuantumResistanceDelta", NumberHelper::formatPercentage),
    IMAGINARY_RESISTANCE_DELTA("ImaginaryResistanceDelta", NumberHelper::formatPercentage),
    SPEED_DELTA("SpeedDelta", NumberHelper::format),
    ;

    @NotNull
    private final String key;

    @NotNull
    private final IValueAcceptor acceptor;

    @Nullable
    public static SRAppendProp fromKey(@NotNull String key) {
        for (SRAppendProp prop : values()) {
            if (prop.getKey().equals(key)) {
                return prop;
            }
        }
        return null;
    }
}
