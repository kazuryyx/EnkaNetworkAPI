package me.kazury.enkanetworkapi.util;

import org.jetbrains.annotations.NotNull;

public class StringUtils {
    @NotNull
    public static String lowerFirstLetter(@NotNull String input) {
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }
}
