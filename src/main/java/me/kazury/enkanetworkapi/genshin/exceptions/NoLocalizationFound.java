package me.kazury.enkanetworkapi.genshin.exceptions;

public class NoLocalizationFound extends RuntimeException {
    public NoLocalizationFound() {
        super("Id does not exist on localization");
    }
}
