package retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit.Auth.AuthenticationInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    public String BASE_URL = "https://etps3.000webhostapp.com/ws/";//"http://192.168.43.146/medicanet_ws/"; //http://167.99.116.11/medicanet/api-rest/
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
    public final Object createService(@NotNull Class serviceClass, @Nullable final String authToken) {
        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };
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
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        this.httpClient = new OkHttpClient.Builder();
        this.builder = new retrofit2.Retrofit.Builder();
        this.builder.baseUrl(this.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson));
        this.retrofit2 = this.builder.build();
    }
}
