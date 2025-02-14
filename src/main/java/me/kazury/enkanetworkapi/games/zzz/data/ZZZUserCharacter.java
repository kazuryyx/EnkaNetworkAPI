package me.kazury.enkanetworkapi.games.zzz.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO DOCS
 */
public class ZZZUserCharacter {
    private final int characterId;
    private final long obtainedAt;
    private final int level;
    private final int weaponEffectState;
    private final boolean hidden;
    private final List<Integer> claimedPromotionRewards;
    private final int promotion;
    private final int coreSkillEnhancementLevel;
    private final int skinId;
    private final int mindscapeLevel;
    private final int experience;

    private ZZZCharacterWeapon weapon;

    public ZZZUserCharacter(
            final int characterId,
            final long obtainedAt,
            final int level,
            final int weaponEffectState,
            final boolean hidden,
            @NotNull List<Integer> claimedPromotionRewards,
            final int promotion,
            final int coreSkillEnhancementLevel,
            final int skinId,
            final int mindscapeLevel,
            final int experience
    ) {
        this.characterId = characterId;
        this.obtainedAt = obtainedAt;
        this.level = level;
        this.weaponEffectState = weaponEffectState;
        this.hidden = hidden;
        this.claimedPromotionRewards = claimedPromotionRewards;
        this.promotion = promotion;
        this.coreSkillEnhancementLevel = coreSkillEnhancementLevel;
        this.skinId = skinId;
        this.mindscapeLevel = mindscapeLevel;
        this.experience = experience;
    }

    /**
     * The agent id of this character.
     */
    public int getCharacterId() {
        return this.characterId;
    }

    /**
     * Gets the signature weapon effect state.
     * <ul>
     *     <li>0: No weapon effect</li>
     *     <li>1: Enabled</li>
     *     <li>2: Disabled</li>
     * </ul>
     */
    public int getWeaponEffectState() {
        return this.weaponEffectState;
    }

    /**
     * The level of this character.
     * <br>Ranges from 1 to 60.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * The unix timestamp of when the character was obtained.
     */
    public long getObtainedAt() {
        return this.obtainedAt;
    }

    /**
     * A list of claimed promotion level rewards.
     * <br>
     * <br>If this list does not contain either 1, 3 or 5, then the player forgor to claim the reward.
     * <br>(or the character is not promoted up to that promotion yet)
     * <br>
     * <br>Can also be empty if the character is not promoted at all.
     */
    @NotNull
    public List<Integer> getClaimedPromotionRewards() {
        return this.claimedPromotionRewards;
    }

    /**
     * Gets the core skill enhancement level of this character.
     * <br>Ranges from 0 to 6.
     */
    public int getCoreSkillEnhancementLevel() {
        return this.coreSkillEnhancementLevel;
    }

    /**
     * Gets the skin id of this character.
     * <br>Will be 0 if the character has no skin equipped.
     */
    public int getSkinId() {
        return this.skinId;
    }

    /**
     * Gets the mindscape talent level of this character.
     */
    public int getMindscapeLevel() {
        return this.mindscapeLevel;
    }

    /**
     * Gets the promotion level of this character.
     * <br>Ranges from 1 to 6.
     */
    public int getPromotion() {
        return this.promotion;
    }

    /**
     * Gets the experience of this character.
     * <br>Idk what this means, I don't play this game.
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * If this character is hidden.
     */
    public boolean isHidden() {
        return this.hidden;
    }

    /**
     * Gets the weapon that this character has equipped. (W-Engine)
     * <br>The character can also not have a Weapon (W-Engine) equipped.
     */
    @Nullable
    public ZZZCharacterWeapon getWeapon() {
        return this.weapon;
    }

    /**
     * Sets a weapon on this object.
     * <br>The character is allowed to not have a weapon equipped, however setting it to null again would be stupid.
     */
    protected void setWeapon(@NotNull ZZZCharacterWeapon weapon) {
        this.weapon = weapon;
    }
}