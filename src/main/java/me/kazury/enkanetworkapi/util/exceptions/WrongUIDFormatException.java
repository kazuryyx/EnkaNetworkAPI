package me.kazury.enkanetworkapi.util.exceptions;

public class WrongUIDFormatException extends RuntimeException {
    public WrongUIDFormatException() {
        super("UID is not in the correct format!");
    }
}
