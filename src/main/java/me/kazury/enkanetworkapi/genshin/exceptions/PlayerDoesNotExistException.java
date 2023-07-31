package me.kazury.enkanetworkapi.genshin.exceptions;

public class PlayerDoesNotExistException extends RuntimeException {
    public PlayerDoesNotExistException() {
        super("Player does not exist by given uid!");
    }
}
