package retrofit.Interfaces;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import clasesResponse.CentroMedicoModel;
import clasesResponse.CitasModel;
import clasesResponse.ConsultaDetalleModel;
import clasesResponse.ConsultaModel;
import clasesResponse.DatosMedicosModel;
import clasesResponse.EntregaMedicamentoDetalleModel;
import clasesResponse.EntregaMedicamentosModel;
import clasesResponse.HistorialModel;
import clasesResponse.MedicamentosModel;
import clasesResponse.MedicamentosPendientesModel;
import clasesResponse.PacientesModel;
import clasesResponse.PerfilPacienteModel;
import clasesResponse.RecetaModel;
import clasesResponse.clItems;
import clasesResponse.clPrueba;
import retrofit2.Call;
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

    @GET("CentroMedico/centro_medico?")
    @NotNull
    Call<List<CentroMedicoModel>> getCentroMedico(@Query("cod") int cod);

    @GET("Medicamentos/catalogo?")
    @NotNull
    Call<List<MedicamentosModel>> getMedicamentos(@Query("cod") int cod,@Query("nom") String nom,@Query("des") String des,@Query("med") String med);

    @GET("Consulta/consulta?")
    @NotNull
    Call<List<ConsultaModel>> getConsultas(@Query("per") int per,@Query("med") int med, @Query("cod") int cod, @Query("cmd") int cmd, @Query("fec") String fec, @Query("ntag") String ntag);

    @GET("Consulta/detalle?")
    @NotNull
    Call<List<ConsultaDetalleModel>> getConsultasDetalle(@Query("cod") int cod);

    @GET("Medicos/fechaEstaLibre?")
    @NotNull
    Call<Boolean> getFechaEstaLibre(@Query("fec") int fec, @Query("med") int med);

    @GET("Historial/historial?")
    @NotNull
    Call<List<HistorialModel>> getHistorialPaciente(@Query("per") @NotNull int per);

    @GET("Medicamentos/medicamentosPaciente?")
    @NotNull
    Call<List<MedicamentosPendientesModel>> getMedicamentosPendientes(@Query("per") int per);

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
    Call<Boolean> postEntregaMedicamentoUpdate(@Field("cod") int cod, @Field("est") String est, @Field("fec") String fec);

    @POST("EntregaMedicamento/updateEntregaDetalle")
    @FormUrlEncoded
    Call<Boolean> postEntregaMedicamentoUpdateDetalle(@Field("cod") String cod,@Field("mdc") String mdc,@Field("can") int can, @Field("est") String est, @Field("fec") String fec);

    @GET("Consulta/consulta?")
    @NotNull
    Call<List<CitasModel>> getCitas(@Query("per") int per, @Query("med") int med ,@Query("cod") int cod, @Query("cmd") int cmd, @Query("fec") String fec, @Query("ntaq") String ntag);

    @GET("Consulta/detalle?")
    @NotNull
    Call<List<DatosMedicosModel>> getDatosMedicos(@Query("per") int per, @Query("cod") int cod);

    @POST("Consulta/agregarDetalle")
    @FormUrlEncoded
    Call<Integer> postAgregarDetalleConsulta(@Field("nom") String nom, @Field("dsc") String dsc, @Field("cme") int cme);

    @POST("Consulta/detalle")
    @FormUrlEncoded
    Call<Boolean> postEditarDetalleConsulta(@Field("cod") int cod, @Field("nom") String nom, @Field("des") String des);

    @POST("Consulta/del_detalle")
    @FormUrlEncoded
    Call<Boolean> postEliminarDetalleConsulta(@Field("cod") int cod);

    @GET("Receta/recetasPorConsulta?")
    @NotNull
    Call<List<RecetaModel>> getReceta(@Query("cod") int cod);

    @POST("Receta/receta")
    @FormUrlEncoded
    Call<Boolean> postAgregarRecetaConsulta(@Field("mdc") int mdc, @Field("cme") int cme, @Field("ind") String ind, @Field("can") double can);

    @POST("Receta/del_receta")
    @FormUrlEncoded
    Call<Boolean> postEliminarRecetaConsulta(@Field("mdc") int mdc, @Field("cme") int cme);

    @POST("Consulta/consulta")
    @FormUrlEncoded
    Call<Integer> postAgregarConsulta(@Field("per") int per, @Field("med") int med, @Field("cmd") int cmd, @Field("fec") String fec);

    @POST("Consulta/del_consulta")
    @FormUrlEncoded
    Call<Boolean> postEliminarConsulta(@Field("cod") int cod);

    @GET("Persona/persona?")
    @NotNull
    Call<List<PerfilPacienteModel>> getPerfil(@Query("cod") int cod, @Query("nom") String nom, @Query("fec") String fec, @Query("cor") String cor, @Query("est") String est, @Query("dui") String dui);
}
