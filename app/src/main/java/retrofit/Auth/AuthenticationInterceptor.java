package retrofit.Auth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class AuthenticationInterceptor implements Interceptor {
    private String authToken;

    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        String authToken = this.authToken;

        Request.Builder builder = original.newBuilder().header("Authorization", authToken);
        Request request = builder.build();
        return chain.proceed(request);
    }

    public AuthenticationInterceptor(@Nullable String token) {
        if (token == null) {
            Intrinsics.throwNpe();
        }
        this.authToken = token;
    }
}
