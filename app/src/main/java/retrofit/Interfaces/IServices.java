package retrofit.Interfaces;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import clasesResponse.CentroMedicoModel;
import clasesResponse.CitasModel;
import clasesResponse.ConsultaDetalleModel;
import clasesResponse.ConsultaModel;
import clasesResponse.EntregaMedicamentoDetalleModel;
import clasesResponse.EntregaMedicamentosModel;
import clasesResponse.HistorialModel;
import clasesResponse.MedicamentosModel;
import clasesResponse.MedicamentosPendientesModel;
import clasesResponse.PacientesModel;
import clasesResponse.UpdateEntregaDeMedicamentosModel;
import clasesResponse.clItems;
import clasesResponse.clPrueba;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("Medicamentos/medicamentos_pendientes?")
    @NotNull
    Call<List<MedicamentosPendientesModel>> getMedicamentosPendientes(@Query("cme") int cme, @Query("per") int per);

    @GET("Medicos/pacientesActivos?")
    @NotNull
    Call<List<PacientesModel>> getPacientesActivos(@Query("med") int med);

    @GET("EntregaMedicamento/Entregas?")
    @NotNull
    Call<List<EntregaMedicamentosModel>> getEntregaMedicamentos(@Query("far") int far, @Query("est") String est, @Query("per")
            int per, @Query("med") int med, @Query("ntag") String ntag, @Query("dtag") String dtag, @Query("con") int cod, @Query("fini")String fini, @Query("ffin") String ffin);

    @GET("EntregaMedicamento/entregaDetalle?")
    @NotNull
    Call<List<EntregaMedicamentoDetalleModel>> getEntregaMedicamentosDetalle(@Query("eme") int eme);

    @POST("EntregaMedicamento/updateEntrega")
    @FormUrlEncoded
    Call postEntregaMedicamentoUpdate(@Field("cod") int cod, @Field("est") String est, @Field("fec") int fec);

    @GET("Consulta/consulta?")
    @NotNull
    Call<List<CitasModel>> getCitas(@Query("per") int per, @Query("doc") int doc, @Query("cod") int cod);


}
