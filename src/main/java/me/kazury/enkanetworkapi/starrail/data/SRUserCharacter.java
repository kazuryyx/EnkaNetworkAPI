package me.kazury.enkanetworkapi.starrail.data;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This class is different from {@link SRCharacterData}
 * <br>This class is used to get the character data from the user.
 * <br>While {@link SRCharacterData} is used to get the character data from the game.
 */
public class SRUserCharacter {
    private final int avatarId;
    private final int eidolon;
    private final int level;
    private final int ascension;

    @Nullable
    private final SRLightcone lightcone;

    @NotNull
    private final List<SRRelic> relics;

    public SRUserCharacter(final int avatarId,
                           final int eidolon,
                           final int level,
                           final int ascension,
                           @Nullable SRLightcone lightcone,
                           @NotNull List<SRRelic> relics) {
        this.avatarId = avatarId;
        this.eidolon = eidolon;
        this.level = level;
        this.ascension = ascension;
        this.lightcone = lightcone;
        this.relics = relics;
    }

    /**
     * The id of the character.
     */
    public int getAvatarId() {
        return this.avatarId;
    }

    /**
     * Represents the eidolon of the character.
     * <br>In Genshin terms: Constellation.
     */
    public int getEidolon() {
        return this.eidolon;
    }

    /**
     * Represents the current level of the character.
     * <br>This is a range from 0 to 80.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Represents the current ascension of the character.
     */
    public int getAscension() {
        return this.ascension;
    }

    /**
     * The weapon that this character currently has equipped.
     * <br>If the character has no weapon equipped, then this will be null.
     * @see SRLightcone
     */
    @Nullable
    public SRLightcone getLightcone() {
        return this.lightcone;
    }

    /**
     * Represents a relic that the character currently has equipped
     * <br>A character can have up to 6 relics equipped, including 4 sub stats on each.
     * @see SRRelic
     * @see SRRelicType
     */
    @NotNull
    public List<SRRelic> getRelics() {
        return this.relics;
    }

    /**
     * @see SRCharacterData
     * @throws NullPointerException if the library version is not the version of genshin.
     * @return The game data of this character, you can use this to get everything that this class does not provide.
     */
    @NotNull
    public SRCharacterData getGameData() {
        final SRCharacterData data = EnkaCaches.getSRCharacterData(String.valueOf(this.avatarId));
        if (data == null) throw new UpdateLibraryException();
        return data;
    }

    /**
     * @return Builder for {@link SRUserCharacter}
     */
    @NotNull
    public static SRUserCharacterBuilder builder() {
        return new SRUserCharacterBuilder();
    }

    public static class SRUserCharacterBuilder {
        private int avatarId;
        private int eidolon;
        private int level;
        private int ascension;
        private SRLightcone lightcone;
        private List<SRRelic> relics;

        @NotNull
        public SRUserCharacterBuilder avatarId(final int avatarId) {
            this.avatarId = avatarId;
            return this;
        }

        @NotNull
        public SRUserCharacterBuilder eidolon(final int eidolon) {
            this.eidolon = eidolon;
            return this;
        }

        @NotNull
        public SRUserCharacterBuilder level(final int level) {
            this.level = level;
            return this;
        }

        @NotNull
        public SRUserCharacterBuilder ascension(final int ascension) {
            this.ascension = ascension;
            return this;
        }

        @NotNull
        public SRUserCharacterBuilder lightcone(@Nullable SRLightcone lightcone) {
            this.lightcone = lightcone;
            return this;
        }

        @NotNull
        public SRUserCharacterBuilder relics(@NotNull List<SRRelic> relics) {
            this.relics = relics;
            return this;
        }

        @NotNull
        public SRUserCharacter build() {
            return new SRUserCharacter(this.avatarId,
                    this.eidolon,
                    this.level,
                    this.ascension,
                    this.lightcone,
                    this.relics);
        }
    }
}
