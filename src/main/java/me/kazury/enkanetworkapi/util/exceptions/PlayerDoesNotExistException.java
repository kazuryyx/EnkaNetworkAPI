package me.kazury.enkanetworkapi.util.exceptions;

public class PlayerDoesNotExistException extends RuntimeException {
    public PlayerDoesNotExistException() {
        super("Player does not exist by given uid!");
    }
}
