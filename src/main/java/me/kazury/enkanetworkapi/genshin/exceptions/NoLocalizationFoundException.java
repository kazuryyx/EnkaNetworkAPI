package me.kazury.enkanetworkapi.genshin.exceptions;

public class NoLocalizationFoundException extends RuntimeException {
    public NoLocalizationFoundException() {
        super("Localization cannot be null!");
    }
}
