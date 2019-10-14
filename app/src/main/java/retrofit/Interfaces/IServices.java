package retrofit.Interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import clasesResponse.clItems;
import clasesResponse.clPrueba;
import clasesResponse.clVersion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IServices {

    @GET("consulta_api.php")
    Call<clPrueba> getConsultaApi();

    @GET("version_api.php?")
    @NotNull
    Call<clItems> getConsultaVersion(@Query("id") @NotNull String id);

}
