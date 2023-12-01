package me.kazury.enkanetworkapi.util;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.BiConsumer;

/**
 * A functional callback which is used for HTTP requests to the API
 */
public class FunctionalCallback implements Callback {
    @Nullable
    private final BiConsumer<Call, IOException> failure;

    @Nullable
    private final IOBiConsumer<Call, Response> success;

    public FunctionalCallback(@Nullable BiConsumer<Call, IOException> failure, @Nullable IOBiConsumer<Call, Response> success) {
        this.failure = failure;
        this.success = success;
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException exception) {
        if (this.failure == null) return;
        this.failure.accept(call, exception);
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (this.success == null) return;
        this.success.accept(call, response);
    }

    /**
     * @return A new builder for {@link FunctionalCallback}
     */
    @NotNull
    public static FunctionalCallbackBuilder builder() {
        return new FunctionalCallbackBuilder();
    }

    public static class FunctionalCallbackBuilder {
        @Nullable
        private BiConsumer<Call, IOException> failure;

        @Nullable
        private IOBiConsumer<Call, Response> success;

        @NotNull
        public FunctionalCallbackBuilder failure(@Nullable BiConsumer<Call, IOException> failure) {
            this.failure = failure;
            return this;
        }

        @NotNull
        public FunctionalCallbackBuilder success(@Nullable IOBiConsumer<Call, Response> success) {
            this.success = success;
            return this;
        }

        @NotNull
        public FunctionalCallback build() {
            return new FunctionalCallback(
                    this.failure,
                    this.success);
        }
    }
}
