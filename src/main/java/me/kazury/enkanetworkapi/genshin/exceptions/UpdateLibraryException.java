package me.kazury.enkanetworkapi.genshin.exceptions;

public class UpdateLibraryException extends RuntimeException {
    public UpdateLibraryException() {
        super("You tried to do an action that is not allowed on this library version. Either update or find a different way.");
    }
}
