package me.kazury.enkanetworkapi.util.calculator.genshin;

import me.kazury.enkanetworkapi.enka.EnkaCaches;
import me.kazury.enkanetworkapi.enka.EnkaVerifier;
import me.kazury.enkanetworkapi.genshin.data.*;
import me.kazury.enkanetworkapi.util.Pair;
import me.kazury.enkanetworkapi.util.calculator.BaseCalculator;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * A basic class which calculates talent materials from a character.
 */
public class GenshinTalentCalculator implements BaseCalculator<GenshinAscensionMaterial> {
    private final int characterId;
    private final GenshinTalentDesire talentDesire;
    private final GenshinCharacterTalents currentTalents;

    public GenshinTalentCalculator(final int characterId,
                                   @NotNull GenshinTalentDesire talentDesire,
                                   @NotNull GenshinCharacterTalents currentTalents) {
        this.characterId = characterId;
        this.talentDesire = talentDesire;
        this.currentTalents = currentTalents;
    }

    /**
     * Returns a pair of talent information (CurrentTalentLevel, WantedTalentLevel) based on an enum.
     */
    @NotNull
    private Pair<Integer, Integer> getTalentPair(@NotNull Talent talentType) {
        return switch (talentType) {
            case NA -> new Pair<>(this.currentTalents.getNormalAttackLevel(), this.talentDesire.getWantedLevelAttack());
            case E -> new Pair<>(this.currentTalents.getElementalSkillLevel(), this.talentDesire.getWantedLevelSkill());
            case Q -> new Pair<>(this.currentTalents.getElementalBurstLevel(), this.talentDesire.getWantedLevelBurst());
        };
    }

    /**
     * Returns a pair (first = mora cost), (second = [material])
     */
    @NotNull
    private Pair<Integer, List<GenshinAscensionMaterial>> loopFrom(@NotNull GenshinCharacterData data,
                                        @NotNull Talent talentType) {
        final int skillId = data.getSkillOrder().get(talentType.ordinal());
        final int proudSkillGroupId = EnkaCaches.getAvatarSkillConfig()
                .stream()
                .filter(avatarSkill -> avatarSkill.getId() == skillId)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .getProudSkillGroupId();

        final Pair<Integer, Integer> talentPair = this.getTalentPair(talentType);
        final int talentNow = talentPair.getFirst();
        final int talentWanted = talentPair.getSecond();

        final List<GenshinAscensionMaterial> materials = new ArrayList<>();
        int moraCost = 0;
        for (int i = talentNow + 1; i < talentWanted + 1; i++) {
            final int finalI = i;
            final GenshinProudSkillData skillData = EnkaCaches.getProudSkillData()
                    .stream()
                    .filter(sData -> sData.getProudSkillGroupId() == proudSkillGroupId)
                    .filter(sData -> sData.getLevel() == finalI)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);

            moraCost += skillData.getMoraCost();

            for (GenshinCostItem item : skillData.getCostItems()) {
                final int id = item.getMaterialId();
                if (id == 0) continue;
                final GenshinMaterial material = EnkaCaches.getMaterial(id);

                materials.add(new GenshinAscensionMaterial(material, item.getCount()));
            }
        }
        return new Pair<>(moraCost, materials);
    }

    /**
     * Calculates the needed materials to get this character's talents up.
     * <br>Returns a list of materials needed for levelling.
     */
    @Override
    @NotNull
    public List<GenshinAscensionMaterial> calculate() {
        EnkaVerifier.verifyNotZero(this.characterId);
        EnkaVerifier.verifyNotNull(this.talentDesire, this.currentTalents);

        final int naLevel = this.talentDesire.getWantedLevelAttack();
        final int eLevel = this.talentDesire.getWantedLevelSkill();
        final int qLevel = this.talentDesire.getWantedLevelBurst();

        // talent desire might be null before calling the 3 methods above, so we have to check it here not with the others
        EnkaVerifier.verifyNotZero(naLevel, eLevel, qLevel);

        final GenshinCharacterData data = EnkaCaches.getGenshinCharacterData(String.valueOf(this.characterId));
        if (data == null) {
            throw new IllegalArgumentException("Character data could not be obtained, wrong id?");
        }

        final Map<Integer, GenshinAscensionMaterial> materials = new HashMap<>();
        int moraCost = 0;
        for (Talent talent : Talent.values()) {
            final Pair<Integer, List<GenshinAscensionMaterial>> pair = this.loopFrom(data, talent);
            for (GenshinAscensionMaterial material : pair.getSecond()) {
                final GenshinMaterial item = material.getItem();
                if (item == null) continue;
                final int materialId = item.getId();

                final GenshinAscensionMaterial existing = materials.get(materialId);
                if (existing != null) {
                    existing.setAmount(existing.getAmount() + material.getAmount());
                    continue;
                }

                materials.put(materialId, material);
            }

            moraCost += pair.getFirst();
        }

        final List<GenshinAscensionMaterial> materialList = new ArrayList<>(materials.values());
        if (moraCost != 0) {
            final GenshinAscensionMaterial mora = new GenshinAscensionMaterial(EnkaCaches.getMaterial(202), moraCost);
            materialList.add(mora);
        }
        return materialList;
    }

    /**
     * An enum that represents a talent every character has.
     */
    private enum Talent {
        NA,
        E,
        Q
    }
}
