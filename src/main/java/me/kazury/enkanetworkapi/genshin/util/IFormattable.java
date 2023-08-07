package me.kazury.enkanetworkapi.genshin.util;

import org.jetbrains.annotations.NotNull;

public interface IFormattable {
    /**
     * Gets the stat of this artifact, such as HP, ATK, DEF%, Element%, Crit
     *
     * <ul>
     *  <li>ATK (Base)</li>
     *   <li>CRIT Rate%</li>
     *   <li>CRIT DMG%</li>
     *   <li>HP%</li>
     *   <li>Energy Recharge%</li>
     *   <li>Elemental Mastery</li>
     *   <li>DEF%</li>
     *   <li>ATK%</li>
     *    <li>Physical DMG Bonus%</li>
     * </ul>
     */
    @NotNull
    String getStat();

    /**
     * Gets the formatted value of this stat (removing .0 from double value).
     * <br>for example:
     * <ul>
     *     <li>HP: 1234</li>
     *     <li>Energy Recharge: 123%</li>
     * </ul>
     */
    @NotNull
    String getFormattedValue();

    /**
     * Gets the raw value of this stat
     * <ul>
     *     <li>HP: 1234.0</li>
     *     <li>Energy Recharge: 123.0</li>
     * </ul>
     */
    double getRawValue();
}
