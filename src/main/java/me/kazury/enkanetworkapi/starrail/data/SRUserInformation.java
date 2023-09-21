package me.kazury.enkanetworkapi.starrail.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.kazury.enkanetworkapi.enka.EnkaNetworkAPI;
import me.kazury.enkanetworkapi.enka.EnkaNetworkBuilder;
import me.kazury.enkanetworkapi.starrail.data.conversion.SRUnconvertedUser;
import me.kazury.enkanetworkapi.util.GlobalLocalization;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * TODO - Add documentation
 */
@Builder
@Getter
@Setter
public class SRUserInformation {
    /**
     * TODO - Add documentation
     */
    private String nickname;
    /**
     * TODO - Add documentation
     */
    private int level;
    /**
     * TODO - Add documentation
     */
    private String signature;
    /**
     * TODO - Add documentation
     */
    private long uid;
    /**
     * TODO - Add documentation
     */
    private int fwiends;
    /**
     * TODO - Add documentation
     */
    private int equilibriumLevel;
    /**
     * TODO - Add documentation
     */
    private SRPlatform platform;
    /**
     * TODO - Add documentation
     */
    private List<SRUserCharacter> detailCharacters;

    protected void doActionAfter(@NotNull Consumer<SRUserInformation> info) {
        info.accept(this);
    }

    /**
     * TODO - Add documentation
     */
    @NotNull
    //@SuppressWarnings("unchecked")
    public static SRUserInformation fromEnkaUser(@NotNull SRUnconvertedUser enkaUser) {
        // You're always free to do a PR and clean this mess up. :)
        final SRUnconvertedUser.DetailInfo detailInfoData = enkaUser.getDetailInfo();
        final SRUserInformation user = SRUserInformation.builder()
                .nickname(detailInfoData.getNickname())
                .level(detailInfoData.getLevel())
                .signature(detailInfoData.getSignature())
                .uid(detailInfoData.getUid())
                .fwiends(detailInfoData.getFriendCount())
                .equilibriumLevel(detailInfoData.getWorldLevel())
                .platform(SRPlatform.valueOf(detailInfoData.getPlatform().toUpperCase()))
                .build();

        user.doActionAfter(data -> {
            if (detailInfoData.isDisplayAvatar()) {
                data.setDetailCharacters(enkaUser.getDetailInfo().getAvatarDetailList().stream().map(avatar -> {
                    SRLightcone lightcone = null;

                    final int eidolon = avatar.getRank();
                    final int ascension = avatar.getPromotion();
                    final int level = avatar.getLevel();
                    final int avatarId = avatar.getAvatarId();
                    final List<SRRelic> relics = new ArrayList<>();

                    // Adding weapon to variable
                    final SRUnconvertedUser.EquipmentInfo equipment = avatar.getEquipment();
                    if (equipment != null) {
                        final List<SRLightcone.LightconeStat> stats = new ArrayList<>();

                        // Getting weapon sub stat information
                        final SRUnconvertedUser.EquipmentFlatData flat = equipment.get_flat();
                        for (SRUnconvertedUser.EquipmentFlatProp subData : flat.getProps()) {
                            final SRAppendProp parsedProp = SRAppendProp.fromKey(subData.getType());
                            if (parsedProp == null) continue;
                            final double rawValue = subData.getValue();

                            stats.add(new SRLightcone.LightconeStat(
                                    parsedProp.getKey(),
                                    parsedProp.getAcceptor().accept(rawValue),
                                    rawValue
                            ));
                        }

                        lightcone = SRLightcone.builder()
                                .superImposion(equipment.getRank())
                                .promotion(equipment.getPromotion())
                                .level(equipment.getLevel())
                                .stats(stats)
                                .hash(flat.getName())
                                .build();
                    }

                    // Getting relic information
                    for (SRUnconvertedUser.RelicData relicData : avatar.getRelicList()) {
                        final int relicLevel = relicData.getLevel();
                        final SRUnconvertedUser.RelicFlatData flat = relicData.get_flat();
                        final List<SRRelic.RelicStat> subStats = new ArrayList<>();

                        // Retrieve relic sub stats, first one is always the main stat
                        final List<SRUnconvertedUser.RelicFlatProp> props = flat.getProps();

                        for (int i = 0; i < props.size(); i++) {
                            if (i == 0) continue; // The main stat is not a sub stat
                            final SRUnconvertedUser.RelicFlatProp subData = props.get(i);
                            final SRAppendProp parsedProp = SRAppendProp.fromKey(subData.getType());
                            if (parsedProp == null) continue;

                            subStats.add(new SRRelic.RelicStat(
                                    parsedProp.getKey(),
                                    parsedProp.getAcceptor().accept(subData.getValue()),
                                    subData.getValue()
                            ));
                        }

                        final SRUnconvertedUser.RelicFlatProp mainStat = props.get(0);
                        final SRAppendProp prop = SRAppendProp.fromKey(mainStat.getType());
                        if (prop == null) continue;

                        final SRRelic.RelicStat main = new SRRelic.RelicStat(
                                prop.getKey(),
                                prop.getAcceptor().accept(mainStat.getValue()),
                                mainStat.getValue()
                        );


                        relics.add(SRRelic.builder()
                                .level(relicLevel)
                                .hash(flat.getSetName())
                                .mainStat(main)
                                .subStats(subStats)
                                .type(SRRelicType.fromId(relicData.getType()))
                                .build());
                    }

                    return SRUserCharacter.builder()
                            .eidolon(eidolon)
                            .ascension(ascension)
                            .relics(relics)
                            .level(level)
                            .avatarId(avatarId)
                            .lightcone(lightcone)
                            .build();
                }).toList());
            } else {
                data.setDetailCharacters(new ArrayList<>());
            }
        });

        return user;
    }
}
