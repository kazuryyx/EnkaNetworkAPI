package me.kazury.enkanetworkapi.enka.page;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents profile data for the Enka profile page.
 * <br>The profile page is for example: <a href="https://enka.network/u/kazury">https://enka.network/u/kazury</a>
 * <br>API Endpoint: GET <a href="https://enka.network/api/profile/Algoinde/">https://enka.network/api/profile/Algoinde/</a>
 */
public class EnkaProfileData {
    private String username;
    private ProfileData profile;
    private int id;

    private String detail;

    /**
     * The username of this profile, this is also the name you provided in
     */
    @NotNull
    public String getUsername() {
        return this.username;
    }

    /**
     * The sequential ID of the user.
     * <br>Or well, how many users registered before this profile.
     */
    public int getId() {
        return this.id;
    }

    /**
     * API Detail, this will only exist if the profile name is not registered.
     * <br>This means that all other fields are not available if this exists. So you should always check this.
     */
    @Nullable
    public String getDetail() {
        return this.detail;
    }

    /**
     * Static profile data
     */
    public static class ProfileData {
        private String bio;
        private int level;

        private String avatar;
        private String image_url;

        /**
         * Gets the bio of the user.
         * <br>If no bio is set, it will return an empty string.
         */
        @NotNull
        public String getBio() {
            return this.bio;
        }

        /**
         * Gets the Patreon level of the user.
         * <ul>
         *    <li>0: Was never subbed</li>
         *    <li>-1: Was subbed, unsubbed</li>
         *    <li>1-3: Patreon Tier</li>
         * </ul>
         */
        public int getPatreonLevel() {
            return this.level;
        }

        /**
         * Gets the avatar URL of the user.
         */
        @NotNull
        public String getAvatarUrl() {
            return this.avatar != null ? this.avatar : this.image_url;
        }
    }

    /**
     * Gets profile data of the user.
     * <br>It will return null if the profile name is not registered.
     * @see #getDetail()
     */
    @Nullable
    public ProfileData getProfile() {
        return profile;
    }
}
