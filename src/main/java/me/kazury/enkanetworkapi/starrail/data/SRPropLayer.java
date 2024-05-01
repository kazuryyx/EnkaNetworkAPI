package me.kazury.enkanetworkapi.starrail.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.util.exceptions.UpdateLibraryException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

/**
 * A prop layer for Honkai: Star Rail.
 */
public class SRPropLayer {
    private String id;
    private boolean disabled;
    private double baseHp;
    private double hpAddedRatio;
    private double hpDelta;
    private double hpConvert;
    private double baseAttack;
    private double attackAddedRatio;
    private double attackDelta;
    private double attackConvert;
    private double baseDefence;
    private double defenceAddedRatio;
    private double defenceDelta;
    private double defenceConvert;
    private double baseSpeed;
    private double speedAddedRatio;
    private double speedDelta;
    private double speedConvert;
    private double criticalChance;
    private double criticalChanceBase;
    private double criticalDamage;
    private double criticalDamageBase;
    private double spRatio;
    private double spRatioConvert;
    private double statusProbability;
    private double statusProbabilityConvert;
    private double statusResistance;
    private double statusResistanceConvert;
    private double healRatioBase;
    private double healRatioConvert;
    private double healTakenRatio;
    private double shieldAddedRatio;
    private double shieldTakenRatio;
    private double aggroBase;
    private double aggroAddedRatio;
    private double aggroDelta;
    private double breakDamageAddedRatio;
    private double breakDamageAddedRatioBase;
    private double allDamageTypeResistance;
    private double physicalResistanceDelta;
    private double fireResistanceDelta;
    private double iceResistanceDelta;
    private double thunderResistanceDelta;
    private double quantumResistanceDelta;
    private double imaginaryResistanceDelta;
    private double windResistanceDelta;
    private double physicalPenetrate;
    private double firePenetrate;
    private double icePenetrate;
    private double thunderPenetrate;
    private double quantumPenetrate;
    private double imaginaryPenetrate;
    private double windPenetrate;
    private double allDamageTypeTakenRatio;
    private double physicalTakenRatio;
    private double fireTakenRatio;
    private double iceTakenRatio;
    private double thunderTakenRatio;
    private double quantumTakenRatio;
    private double imaginaryTakenRatio;
    private double windTakenRatio;
    private double allDamageTypeAddedRatio;
    private double dotDamageAddedRatio;
    private double physicalAddedRatio;
    private double fireAddedRatio;
    private double iceAddedRatio;
    private double thunderAddedRatio;
    private double quantumAddedRatio;
    private double imaginaryAddedRatio;
    private double windAddedRatio;
    private double stanceBreakAddedRatio;
    private double allDamageReduce;
    private double fatigueRatio;
    private double minimumFatigueRatio;

    public SRPropLayer(@Nullable String id) {
        this.id = id != null ? id : "";
        this.disabled = false;

        // HP = HP_BASE * (1 + HP_AddedRatio) + HP_Delta + HP_CONVERT
        this.baseHp = 0;
        this.hpAddedRatio = 0;
        this.hpDelta = 0;
        this.hpConvert = 0;

        // Attack = Attack_BASE * (1 + Attack_AddedRatio) + Attack_Delta + Attack_CONVERT = 0
        this.baseAttack = 0;
        this.attackAddedRatio = 0;
        this.attackDelta = 0;
        this.attackConvert = 0;

        // Defence = Defence_BASE * (1 + Defence_AddedRatio) + Defence_Delta + Defence_CONVERT = 0
        this.baseDefence = 0;
        this.defenceAddedRatio = 0;
        this.defenceDelta = 0;
        this.defenceConvert = 0;

        // Speed = Speed_BASE * (1 + Speed_AddedRatio) + Speed_Delta + Speed_CONVERT = 0
        this.baseSpeed = 0;
        this.speedAddedRatio = 0;
        this.speedDelta = 0;
        this.speedConvert = 0;

        // Crit = 0
        this.criticalChance = 0;
        this.criticalDamage = 0;

        // Energy Regen = 0
        this.spRatio = 0;
        this.spRatioConvert = 0;

        // Effect Hit Rate = 0
        this.statusProbability = 0;
        this.statusProbabilityConvert = 0;

        // Effect RES = 0
        this.statusResistance = 0;
        this.statusResistanceConvert = 0;

        // Increases heal strength that are created by target = 0
        this.healRatioBase = 0;
        this.healRatioConvert = 0;

        // Increases heal strength that are applied to target = 0
        this.healTakenRatio = 0;

        // Increases shield strength that are created by target = 0
        this.shieldAddedRatio = 0;

        // Increases shield strength that are applied to target = 0
        this.shieldTakenRatio = 0;

        // AGGRO = AGGRO_BASE * (1 + AGGRO_AddedRatio) + AGGRO_Delta = 0
        this.aggroBase = 0;
        this.aggroAddedRatio = 0;
        this.aggroDelta = 0;

        // Break Effect = 0
        this.breakDamageAddedRatio = 0;
        this.breakDamageAddedRatioBase = 0;

        // Damage Resistances (RES = ALL_DMG_RES + ELEMENT_DMG_RES) = 0
        this.allDamageTypeResistance = 0;
        this.physicalResistanceDelta = 0;
        this.fireResistanceDelta = 0;
        this.iceResistanceDelta = 0;
        this.thunderResistanceDelta = 0;
        this.quantumResistanceDelta = 0;
        this.imaginaryResistanceDelta = 0;
        this.windResistanceDelta = 0;

        // Elemental Penetrates = 0
        this.physicalPenetrate = 0;
        this.firePenetrate = 0;
        this.icePenetrate = 0;
        this.thunderPenetrate = 0;
        this.quantumPenetrate = 0;
        this.imaginaryPenetrate = 0;
        this.windPenetrate = 0;

        // Damage Taken Boost (TAKEN = ALL_DMG_TAKEN + ELEMENT_DMG_TAKEN) = 0
        this.allDamageTypeTakenRatio = 0;
        this.physicalTakenRatio = 0;
        this.fireTakenRatio = 0;
        this.iceTakenRatio = 0;
        this.thunderTakenRatio = 0;
        this.quantumTakenRatio = 0;
        this.imaginaryTakenRatio = 0;
        this.windTakenRatio = 0;

        // DMG% increases (DMG% = ALL_DMG% + ELEMENT_DMG% + DOT_DMG%) = 0
        this.allDamageTypeAddedRatio = 0;
        this.dotDamageAddedRatio = 0;
        this.physicalAddedRatio = 0;
        this.fireAddedRatio = 0;
        this.iceAddedRatio = 0;
        this.thunderAddedRatio = 0;
        this.quantumAddedRatio = 0;
        this.imaginaryAddedRatio = 0;
        this.windAddedRatio = 0;

        // Stance DMG% increase (damage to toughness bar, not break effect) = 0
        this.stanceBreakAddedRatio = 0;

        // Multiplicative DMG reduction TOTAL_DMG_REDUCE = 1 - (1 - CUR_DMG_REDUCE)*(1 - ADDED_DMG_REDUCE)
        // DMG_REDUCE from target attacked, FATIGUE from attacker
        this.allDamageReduce = 0;
        this.fatigueRatio = 0;
        this.minimumFatigueRatio = 0;
    }

    /**
     * @return The hp
     */
    public double getHP() {
        return this.baseHp * (1 + this.hpAddedRatio) + this.hpDelta + this.hpConvert;
    }

    /**
     * @return The attack
     */
    public double getAttack() {
        return this.baseAttack * (1 + this.attackAddedRatio) + this.attackDelta + this.attackConvert;
    }

    /**
     * @return The defence
     */
    public double getDefence() {
        return this.baseDefence * (1 + this.defenceAddedRatio) + this.defenceDelta + this.defenceConvert;
    }

    /**
     * @return The SPD
     */
    public double getSpeed() {
        return this.baseSpeed * (1 + this.speedAddedRatio) + this.speedDelta + this.speedConvert;
    }

    /**
     * @return The break damage
     */
    public double getBreakDamage() {
        return this.breakDamageAddedRatioBase + this.breakDamageAddedRatio;
    }

    /**
     * @return The aggro (IDK what this is)
     */
    public double getAggro() {
        return this.aggroBase * (1 + this.aggroAddedRatio) + this.aggroDelta;
    }

    @NotNull
    public static PropProperty toProp(@NotNull String type, final double value, final double base,
                                      final double added, final double delta, final double convert) {
        return new PropProperty(type, value, base, added, delta, convert);
    }

    /**
     * Get all props from this layer
     * @return A list of prop properties
     */
    @NotNull
    public List<PropProperty> getProps() {
        return List.of(
                toProp("MaxHP", this.getHP(), this.baseHp, this.hpAddedRatio, this.hpDelta, this.hpConvert),
                toProp("Attack", this.getAttack(), this.baseAttack, this.attackAddedRatio, this.attackDelta, this.attackConvert),
                toProp("Defence", this.getDefence(), this.baseDefence, this.defenceAddedRatio, this.defenceDelta, this.defenceConvert),
                toProp("Speed", this.getSpeed(), this.baseSpeed, this.speedAddedRatio, this.speedDelta, this.speedConvert),
                toProp("CriticalChance", this.criticalChance + this.criticalChanceBase, this.criticalChance + this.criticalChanceBase, 0D, 0D, 0D),
                toProp("CriticalDamage", this.criticalDamage + this.criticalDamageBase, this.criticalDamage + this.criticalDamageBase, 0D, 0D, 0D),
                toProp("BreakDamageAddedRatio", this.getBreakDamage(), this.breakDamageAddedRatioBase, this.breakDamageAddedRatio, 0D, 0D),
                toProp("HealRatio", this.healRatioBase, this.healRatioBase, 0D, 0D, this.healRatioConvert),
                toProp("SPRatio", this.spRatio, this.spRatio, 0D, 0D, this.spRatioConvert),
                toProp("StatusProbability", this.statusProbability, this.statusProbability, 0, 0, this.statusProbabilityConvert),
                toProp("StatusResistance", this.statusResistance, this.statusResistance, 0, 0, this.statusResistanceConvert),
                toProp("PhysicalAddedRatio", this.physicalAddedRatio + this.allDamageTypeAddedRatio, this.physicalAddedRatio, 0D, 0D, 0D),
                toProp("FireAddedRatio", this.fireAddedRatio + this.allDamageTypeAddedRatio, this.fireAddedRatio, 0D, 0D, 0D),
                toProp("IceAddedRatio", this.iceAddedRatio + this.allDamageTypeAddedRatio, this.iceAddedRatio, 0D, 0D, 0D),
                toProp("ThunderAddedRatio", this.thunderAddedRatio + this.allDamageTypeAddedRatio, this.thunderAddedRatio, 0D, 0, 0D),
                toProp("QuantumAddedRatio", this.quantumAddedRatio + this.allDamageTypeAddedRatio, this.quantumAddedRatio, 0D, 0, 0D),
                toProp("ImaginaryAddedRatio", this.imaginaryAddedRatio + this.allDamageTypeAddedRatio, this.imaginaryAddedRatio, 0D, 0D, 0D),
                toProp("WindAddedRatio", this.windAddedRatio + this.allDamageTypeAddedRatio, this.windAddedRatio, 0D, 0D, 0D)
        );
    }

    /**
     * Constructs a new character layer
     * @param character The character
     * @return A prop layer
     */
    @NotNull
    public static SRPropLayer character(@NotNull SRUserCharacter character) {
        final int id = character.getAvatarId();
        final int ascension = character.getAscension();
        final int level = character.getLevel();

        final JsonNode topNode = EnkaCaches.getHonkaiMetaCharacterProperties(String.valueOf(id), String.valueOf(ascension));
        final SRPropLayer layer = new SRPropLayer("character");
        if (topNode == null) {
            throw new UpdateLibraryException();
        }

        final double hpBase = topNode.get("HPBase").asDouble();
        final double hpAdd = topNode.get("HPAdd").asDouble();

        final double attackBase = topNode.get("AttackBase").asDouble();
        final double attackAdd = topNode.get("AttackAdd").asDouble();

        final double defenceBase = topNode.get("DefenceBase").asDouble();
        final double defenceAdd = topNode.get("DefenceAdd").asDouble();

        final double speedBase = topNode.get("SpeedBase").asDouble();

        final double critChance = topNode.get("CriticalChance").asDouble();
        final double critDamage = topNode.get("CriticalDamage").asDouble();
        final double baseAggro = topNode.get("BaseAggro").asDouble();

        layer.baseHp = hpBase + hpAdd * (level - 1);
        layer.baseAttack = attackBase + attackAdd * (level - 1);
        layer.baseDefence = defenceBase + defenceAdd * (level - 1);
        layer.baseSpeed = speedBase;
        layer.criticalChance = critChance;
        layer.criticalDamage = critDamage;
        layer.aggroBase = baseAggro;
        layer.spRatio = 1;
        return layer;
    }

    @NotNull
    public static SRPropLayer weapon(@NotNull SRLightcone weapon) {
        final int tid = weapon.getId();
        final int rank = weapon.getPromotion();

        final JsonNode topNode = EnkaCaches.getHonkaiMetaWeaponProperties(String.valueOf(tid), String.valueOf(rank));
        final SRPropLayer layer = new SRPropLayer("weapon");
        if (topNode == null) {
            throw new UpdateLibraryException();
        }

        final double baseHP = topNode.get("BaseHP").doubleValue();
        final double hpAdd = topNode.get("HPAdd").doubleValue();

        final double baseAttack = topNode.get("BaseAttack").doubleValue();
        final double attackAdd = topNode.get("AttackAdd").doubleValue();

        final double baseDefence = topNode.get("BaseDefence").doubleValue();
        final double defenceAdd = topNode.get("DefenceAdd").doubleValue();

        layer.baseHp = baseHP + hpAdd * (weapon.getLevel() - 1);
        layer.baseAttack = baseAttack + attackAdd * (weapon.getLevel() - 1);
        layer.baseDefence = baseDefence + defenceAdd * (weapon.getLevel() - 1);
        return layer;
    }

    public static SRPropLayer weaponAffix(@NotNull SRLightcone weapon) {
        final int tid = weapon.getId();
        final int imposion = weapon.getSuperImposion();

        final JsonNode topNode = EnkaCaches.getHonkaiMetaWeaponAffixProperties(String.valueOf(tid), String.valueOf(imposion));
        final SRPropLayer layer = new SRPropLayer("weaponAffix");
        if (topNode == null) {
            throw new UpdateLibraryException();
        }

        addFields(topNode, layer.getClass(), layer);
        return layer;
    }

    @NotNull
    public static SRPropLayer relic(@NotNull List<SRRelic> relics) {
        final SRPropLayer layer = new SRPropLayer("relics");
        final Class<? extends SRPropLayer> layerClazz = layer.getClass();

        final Map<String, Double> valueMap = new HashMap<>();

        for (SRRelic relic : relics) {
            final List<SRRelic.RelicStat> subStats = new ArrayList<>(relic.getSubStats());

            subStats.add(relic.getMainStat());

            for (SRRelic.RelicStat sub : subStats) {
                final String stat = sub.getStat();

                final double value = ifProp(sub.getRawValue(), stat);
                valueMap.put(stat, valueMap.getOrDefault(stat, 0D) + value);
            }
        }

        for (Map.Entry<String, Double> entry : valueMap.entrySet()) {
            final String key = entry.getKey();
            final double value = entry.getValue();

            final Field field = getField(layerClazz, key);
            if (field == null) continue;

            field.setAccessible(true);

            try {
                field.set(layer, value);
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }

        System.out.println(valueMap);
        return layer;
    }

    @NotNull
    public static SRPropLayer relicSet(@NotNull List<SRRelic> relics) {
        final SRPropLayer layer = new SRPropLayer("relicSet");
        final Class<? extends SRPropLayer> layerClazz = layer.getClass();

        final List<Long> sets = relics.stream()
                .map(SRRelic::getSetId)
                .filter(id -> id != 0)
                .toList();
        final Map<Long, Integer> setNames = new HashMap<>();

        for (long setId : sets) {
            setNames.put(setId, setNames.getOrDefault(setId, 0) + 1);
        }

        final List<Pair<Integer, Integer>> list = setNames.entrySet()
                .stream()
                .map(entry -> new Pair<>(Math.toIntExact(entry.getKey()), entry.getValue() == 3 ? 2 : entry.getValue()))
                .filter(pair -> pair.getSecond() > 1)
                .toList();

        for (Pair<Integer, Integer> pair : list) {
            final int setId = pair.getFirst();
            int count = pair.getSecond();

            final JsonNode setProperties = EnkaCaches.getHonkaiMetaRelicSetProperties(setId, count);
            if (setProperties == null) continue;

            addFields(setProperties, layerClazz, layer);

            count -= 2;
            if (count < 1) continue;
            final JsonNode setProperties2 = EnkaCaches.getHonkaiMetaRelicSetProperties(setId, count);
            if (setProperties2 == null) continue;

            addFields(setProperties2, layerClazz, layer);
        }
        return layer;
    }

    @NotNull
    public static SRPropLayer skillTree(@NotNull List<SRSkillTreeElement> skillTree) {
        final SRPropLayer layer = new SRPropLayer("skillTree");
        final Class<? extends SRPropLayer> layerClazz = layer.getClass();

        for (SRSkillTreeElement element : skillTree) {
            final int traceId = element.getPointId();
            final int level = element.getLevel();

            final JsonNode getNode = EnkaCaches.getHonkaiMetaSkillTreeProperties(String.valueOf(traceId), String.valueOf(level));
            if (getNode == null) {
                continue;
            }

            addFields(getNode, layerClazz, layer);
        }
        return layer;
    }

    @SuppressWarnings("unchecked")
    private static void addFields(@NotNull JsonNode properties,
                                  @NotNull Class<? extends SRPropLayer> layerClazz,
                                  @NotNull SRPropLayer layer) {
        final Map<String, Double> propertiesConverted = new ObjectMapper().convertValue(properties, Map.class);

        for (Map.Entry<String, Double> entry : propertiesConverted.entrySet()) {
            final String key = entry.getKey();
            final double value = entry.getValue();
            final Field field = getField(layerClazz, key);
            if (field == null) {
                System.out.println("No field - " + key);
                continue;
            }

            field.setAccessible(true);

            try {
                final double existingValue = (double) field.get(layer);
                field.set(layer, existingValue + value);
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Nullable
    private static Field getField(@NotNull Class<?> clazz, @NotNull String fieldName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }

    /**
     * A prop state
     */
    public static class PropState {
        private final Map<String, SRPropLayer> layers;

        public PropState() {
            this.layers = new HashMap<>();
        }

        public void addLayer(@NotNull SRPropLayer layer) {
            this.layers.put(layer.id, layer);
        }

        public void clear() {
            this.layers.clear();
        }

        /**
         * Sums all layers together and returns the final layer
         * <br>Honestly man, I hate java. Why is this method so much longer than TypeScript?
         * <br>JUST BECAUSE WE CAN'T LOOP THROUGH OBJECTS IN JAVA
         * @param layers The layer identifier(s) to use
         * @return The layer object with the main props.
         */
        @NotNull
        public SRPropLayer sum(@NotNull String... layers) {
            final SRPropLayer sum = new SRPropLayer(null);

            final Map<String, Double> sumMap = new HashMap<>();

            for (SRPropLayer layer : this.layers.values()) {
                if (layer.disabled) continue;
                if (layers.length > 0 && !Arrays.asList(layers).contains(layer.id)) continue;

                final Field[] fields = layer.getClass().getDeclaredFields();

                for (Field field : fields) {
                    if (field.getName().equals("id") || field.getName().equals("disabled")) {
                        continue;
                    }

                    try {
                        field.setAccessible(true);

                        final Object value = field.get(layer);

                        if (value instanceof Number) {
                            sumMap.put(field.getName(), sumMap.getOrDefault(field.getName(), 0D) + ((Number) value).doubleValue());
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }

            // set it back via reflection
            for (Field field : sum.getClass().getDeclaredFields()) {
                if (field.getName().equals("id") || field.getName().equals("disabled")) continue;

                try {
                    field.setAccessible(true);
                    field.set(sum, sumMap.getOrDefault(field.getName(), 0D));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return sum;
        }
    }

    /**
     * A prop property
     */
    public static class PropProperty {
        private String type;
        private double value;
        private double base;
        private double added;
        private double delta;
        private double convert;

        public PropProperty(@NotNull String type, final double value,
                            final double base, final double added,
                            final double delta, final double convert) {
            this.type = type;
            this.value = value;
            this.base = base;
            this.added = added;
            this.delta = delta;
            this.convert = convert;
        }

        /**
         * The type of this prop
         */
        @NotNull
        public String getType() {
            return type;
        }

        /**
         * The value of this prop
         */
        public double getValue() {
            return value;
        }
    }

    /**
     * Formats a prop
     *
     * @param pr   The prop value
     * @param type The type of the prop
     * @return Formatted prop
     */
    public static double ifProp(Object pr, String type) {
        if (type != null) {
            pr = new Object[]{pr, type};
        }
        if (type != null && type.contains("Speed")) {
            Object[] pair = (Object[]) pr;
            return pair[0] != null ? (Double) pair[0] : 0.0;
        }

        List<String> p = Arrays.asList("Ratio", "Rate", "Chance", "Probability", "Resistance", "Damage");
        if (type != null && p.stream().anyMatch(type::contains)) {
            Object[] pair = (Object[]) pr;
            return ((Double) pair[0]) / 100.0;
        }

        Object[] pair = (Object[]) pr;
        return (Double) pair[0];
    }
}
