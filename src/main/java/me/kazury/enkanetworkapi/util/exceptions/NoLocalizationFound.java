package me.kazury.enkanetworkapi.util.exceptions;

public class NoLocalizationFound extends RuntimeException {
    public NoLocalizationFound() {
        super("Id does not exist on localization");
    }
}
