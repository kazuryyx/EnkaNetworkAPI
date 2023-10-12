package me.kazury.enkanetworkapi.util.exceptions;

public class RateLimitedException extends RuntimeException {
    public RateLimitedException() {
        super("You are being rate limited trying to fetch data");
    }
}
