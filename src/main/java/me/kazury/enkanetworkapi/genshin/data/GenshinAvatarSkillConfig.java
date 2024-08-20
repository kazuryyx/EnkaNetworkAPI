package me.kazury.enkanetworkapi.genshin.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A proud skill data class that represents AvatarSkillExcelConfigData.json.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenshinAvatarSkillConfig {
    @JsonProperty("id")
    private int id;

    @JsonProperty("proudSkillGroupId")
    private int proudSkillGroupId;

    public GenshinAvatarSkillConfig() {}

    /**
     * The id of this avatar skill config. This is used for filtering the skill order to get {@link #getProudSkillGroupId()}
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the proud skill group id. Once this is obtained, we can get the materials for talents.
     */
    public int getProudSkillGroupId() {
        return this.proudSkillGroupId;
    }
}
