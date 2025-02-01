package me.kazury.enkanetworkapi.games.genshin.data;

import org.jetbrains.annotations.Nullable;

/**
 * An object that represents an ascension material for a character, weapon, artifact xp or talent.
 */
public class GenshinAscensionMaterial {
    private final GenshinMaterial item;
    private int amount;

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
        return this.item;
    }

    /**
     * The amount of the item that is required for ascension.
     * <br>For example, for level 90 ascension boss material you need 20 of the item.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Sets the amount for this item, this is only used for talent calculation and should not be used otherwise.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
