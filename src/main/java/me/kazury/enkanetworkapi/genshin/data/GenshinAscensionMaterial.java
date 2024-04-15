package me.kazury.enkanetworkapi.genshin.data;

import org.jetbrains.annotations.Nullable;

/**
 * An object that represents an ascension material for a character or weapon.
 */
public class GenshinAscensionMaterial {
    private final GenshinMaterial item;
    private final int amount;

    public GenshinAscensionMaterial(@Nullable GenshinMaterial item, final int amount) {
        this.item = item;
        this.amount = amount;
    }

    /**
     * The item that is required for ascension.
     *
     * <br>For artifacts, this will be null and {@link GenshinAscensionMaterial#getAmount()} will return the amount of exp needed.
     */
    @Nullable
    public GenshinMaterial getItem() {
        return item;
    }

    /**
     * The amount of the item that is required for ascension.
     * <br>For example, for level 90 ascension boss material you need 20 of the item.
     */
    public int getAmount() {
        return this.amount;
    }
}
