package me.kazury.enkanetworkapi.genshin.util;

import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.genshin.data.GenshinUserInformation;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class NumberHelper {
    private static final DecimalFormat format = new DecimalFormat("###.#");

    /**
     * Formats a number to a string (1000 -> 1.000)
     * @param number the number to format
     * @return the formatted number
     */
    @NotNull
    public static String format(double number) {
        return format.format(number);
    }

    /**
     * Formats a number to a string (1000 -> 1.000%)
     * @param number the number to format
     * @return the formatted number with percentage, this is used for Energy Recharge for example
     */
    @NotNull
    public static String formatPercentage(double number) {
        return format(number) + "%";
    }

    /**
     * There are some enum types which I do not understand how they work, so there has to be a default implementation
     * @param number the number to format
     * @return the number which is given in the parameter
     */
    @NotNull
    public static String none(final double number) {
        return String.valueOf(number);
    }
}
