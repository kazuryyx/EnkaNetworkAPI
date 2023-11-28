package me.kazury.enkanetworkapi.util.exceptions;

public class HonkaiNotEnabledException extends RuntimeException {
    public HonkaiNotEnabledException() {
        super("Honkai has not been enabled. Use enableHonkai(boolean) in your API loader.");
    }
}
