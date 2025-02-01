package me.kazury.enkanetworkapi.games.starrail.data;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @NotNull
    private final List<SRSkillTreeElement> treeElements;

    public SRUserCharacter(final int avatarId,
                           final int eidolon,
                           final int level,
                           final int ascension,
                           @Nullable SRLightcone lightcone,
                           @NotNull List<SRRelic> relics,
                           @NotNull List<SRSkillTreeElement> treeElements) {
        this.avatarId = avatarId;
        this.eidolon = eidolon;
        this.level = level;
        this.ascension = ascension;
        this.lightcone = lightcone;
        this.relics = relics;
        this.treeElements = treeElements;
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
     * Gets the skill tree elements of the character.
     */
    @NotNull
    public List<SRSkillTreeElement> getTreeElements() {
        return treeElements;
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
     * Gets props from this character.
     */
    @NotNull
    public Map<SRAppendProp, SRAppendPropData> getProps() {
        final SRLightcone srLightcone = this.getLightcone();
        final SRPropLayer.PropState state = new SRPropLayer.PropState();

        state.addLayer(SRPropLayer.character(this));

        if (srLightcone != null) {
            state.addLayer(SRPropLayer.weapon(srLightcone));
        }

        if (srLightcone != null && srLightcone.getGameData().getPath() == this.getGameData().getCharacterPath()) {
            // Players can equip lightcones from other paths but the boost only applies for same path
            state.addLayer(SRPropLayer.weaponAffix(srLightcone));
        }

        state.addLayer(SRPropLayer.relic(this.getRelics()));
        state.addLayer(SRPropLayer.relicSet(this.getRelics()));
        state.addLayer(SRPropLayer.skillTree(this.getTreeElements()));

        final Map<SRAppendProp, SRAppendPropData> props = new HashMap<>();
        final SRPropLayer sum = state.sum("weapon", "character", "relics", "relicSet", "weaponAffix", "skillTree");

        for (SRPropLayer.PropProperty prop : sum.getProps()) {
            final SRAppendProp appendProp = SRAppendProp.fromKey(prop.getType());
            if (appendProp == null) {
                System.out.println("No prop for " + prop.getType());
                continue;
            }

            double value = prop.getValue();
            if (appendProp.getValueType() == SRAppendProp.ValueType.PERCENTAGE) {
                value *= 100;
            }

            props.put(appendProp, new SRAppendPropData(appendProp, appendProp.getAcceptor().accept(value), value));
        }
        return props;
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
        private List<SRSkillTreeElement> treeElements;

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
        public SRUserCharacterBuilder treeElements(@NotNull List<SRSkillTreeElement> treeElements) {
            this.treeElements = treeElements;
            return this;
        }

        @NotNull
        public SRUserCharacter build() {
            return new SRUserCharacter(this.avatarId,
                    this.eidolon,
                    this.level,
                    this.ascension,
                    this.lightcone,
                    this.relics,
                    this.treeElements
            );
        }
    }
}
