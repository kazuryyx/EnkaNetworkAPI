package me.kazury.enkanetworkapi.util.exceptions;

public class ZenlessNotEnabledException extends RuntimeException {
    public ZenlessNotEnabledException() {
        super("Zenless has not been enabled. Use enableZenless(boolean) in your API loader.");
    }
}
