package me.kazury.enkanetworkapi.genshin.util;

import java.text.DecimalFormat;

public class NumberHelper {
    private static final DecimalFormat format = new DecimalFormat("###.#");

    public static String format(double number) {
        return format.format(number);
    }

    public static String formatPercentage(double number) {
        return format(number) + "%";
    }

    public static String none(final double number) {
        return String.valueOf(number);
    }
}
