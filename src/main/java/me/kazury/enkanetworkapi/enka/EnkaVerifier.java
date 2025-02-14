package me.kazury.enkanetworkapi.enka;

import me.kazury.enkanetworkapi.util.exceptions.HonkaiNotEnabledException;
import me.kazury.enkanetworkapi.util.exceptions.ZenlessNotEnabledException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A verifier class which is used to verify conditions before executing code.
 */
public class EnkaVerifier {
    /**
     * Verifies a condition, if the condition is false, it will throw an exception.
     * @param condition the condition
     * @param exception the exception
     */
    public static <E extends Exception> void verifyOrThrow(final boolean condition, @NotNull Class<E> exception) throws E {
        if (condition) return;

        try {
            throw exception.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Verifies if honkai is enabled, this is run before all Honkai: Star Rail operations.
     */
    public static void verifyHonkai() {
        verifyOrThrow(EnkaGlobals.isHonkaiEnabled(), HonkaiNotEnabledException.class);
    }

    /**
     * Verifies if zenless is enabled, this is run before all Honkai: Star Rail operations.
     */
    public static void verifyZenless() {
        verifyOrThrow(EnkaGlobals.isZenlessEnabled(), ZenlessNotEnabledException.class);
    }

    /**
     * Verifies that a number is not 0
     */
    public static void verifyNotZero(@NotNull Number... numbers) {
        for (Number i : numbers) {
            verifyOrThrow(i.intValue() != 0, IllegalArgumentException.class);
        }
    }

    /**
     * Verifies that an object is not null
     */
    public static void verifyNotNull(Object... objects) {
        for (Object o : objects) {
            verifyOrThrow(o != null, IllegalArgumentException.class);
        }
    }
}
