package me.kazury.enkanetworkapi.util.calculator;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * The base calculator class that is used among all calculators
 * @param <M> The base material class
 */
public interface BaseCalculator<M> {
    @NotNull
    default List<M> calculate() {
        return Collections.emptyList();
    }
}
