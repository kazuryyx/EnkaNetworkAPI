package me.kazury.enkanetworkapi.enka;

import lombok.SneakyThrows;
import me.kazury.enkanetworkapi.util.exceptions.HonkaiNotEnabledException;
import org.jetbrains.annotations.NotNull;

/**
 * TODO - Add documentation
 */
public class EnkaVerifier {
    /**
     * TODO - Add documentation
     */
    @SneakyThrows
    public static <T extends Exception> void verifyOrThrow(final boolean condition, @NotNull Class<T> exception) {
        if (condition) return;

        try {
            throw exception.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * TODO - Add documentation
     */
    public static void verifyHonkai() {
        verifyOrThrow(EnkaGlobals.isHonkaiEnabled(), HonkaiNotEnabledException.class);
    }
}
