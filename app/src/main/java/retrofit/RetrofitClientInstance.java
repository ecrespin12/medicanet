package retrofit;

import android.text.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit.Auth.AuthenticationInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    public String BASE_URL = "http://167.99.116.11/medicanet/api-rest/";
    private OkHttpClient.Builder httpClient = null;
    private retrofit2.Retrofit.Builder builder = null;
    private Retrofit retrofit2;

    @NotNull
    public final Object createService(@NotNull Class serviceClass) {
        Intrinsics.checkParameterIsNotNull(serviceClass, "serviceClass");
        return this.createService(serviceClass, null, null);
    }

    @NotNull
    public final Object createService(@NotNull Class serviceClass, @Nullable String username, @Nullable String password) {
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return this.createService(serviceClass, authToken);
        } else {
            return this.createService(serviceClass, null);
        }
    }

    @NotNull
    public final Object createService(@NotNull Class serviceClass, @Nullable String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
            if (!this.httpClient.interceptors().contains(interceptor)) {
                this.httpClient.addInterceptor(interceptor);
                this.builder.client(this.httpClient.build());
                this.retrofit2 = this.builder.build();
            }
        }
        return this.retrofit2.create(serviceClass);
    }

    public RetrofitClientInstance() {
        this.httpClient = new OkHttpClient.Builder();
        this.builder = new retrofit2.Retrofit.Builder();
        this.builder.baseUrl(this.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        this.retrofit2 = this.builder.build();
    }
}
