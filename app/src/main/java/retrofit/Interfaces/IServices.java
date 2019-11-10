package retrofit.Interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import clasesResponse.CentroMedicoModel;
import clasesResponse.ConsultaDetalleModel;
import clasesResponse.ConsultaModel;
import clasesResponse.HistorialModel;
import clasesResponse.MedicamentosModel;
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

    @GET("CentroMedico/centro_medico")
    @NotNull
    Call<List<CentroMedicoModel>> getCentroMedico();

    @GET("Medicamentos/catalogo")
    @NotNull
    Call<List<MedicamentosModel>> getMedicamentos();

    @GET("Consulta/consulta?")
    @NotNull
    Call<List<ConsultaModel>> getConsultas(@Query("per") int per, @Query("doc") int doc, @Query("cod") int cod);

    @GET("Consulta/detalle?")
    @NotNull
    Call<List<ConsultaDetalleModel>> getConsultasDetalle(@Query("cod") int cod);

    @GET("Medicos/fechaEstaLibre?")
    @NotNull
    Call<Boolean> getFechaEstaLibre(@Query("fec") int fec, @Query("med") int med);

    @GET("Historial/historial?")
    @NotNull
    Call<List<HistorialModel>> getHistorialPaciente(@Query("per") @NotNull int per);

}
