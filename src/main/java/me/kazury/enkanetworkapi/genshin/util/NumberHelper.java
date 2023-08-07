package me.kazury.enkanetworkapi.genshin.util;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class NumberHelper {
    private static final DecimalFormat format = new DecimalFormat("###.#");

    @NotNull
    public static String format(double number) {
        return format.format(number);
    }

    @NotNull
    public static String formatPercentage(double number) {
        return format(number) + "%";
    }

    @NotNull
    public static String none(final double number) {
        return String.valueOf(number);
    }
}
