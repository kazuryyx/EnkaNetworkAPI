package me.kazury.enkanetworkapi.util;

/**
 * A functional interface which is used for caching data
 */
@FunctionalInterface
public interface IExpiryTime {
    /**
     * @return the time that this object has left to live
     */
    int getTtl();
}
