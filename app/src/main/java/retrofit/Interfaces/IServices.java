package retrofit.Interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.List;

<<<<<<< Updated upstream
=======
import clasesResponse.CentroMedicoModel;
import clasesResponse.ConsultaDetalleModel;
import clasesResponse.ConsultaModel;
import clasesResponse.HistorialModel;
import clasesResponse.MedicamentosPendientesModel;
import clasesResponse.MedicamentosModel;
>>>>>>> Stashed changes
import clasesResponse.clItems;
import clasesResponse.clPrueba;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IServices {

    @GET("consulta_api.php")
    Call<clPrueba> getConsultaApi();

    @GET("version_api.php?")
    @NotNull
    Call<clItems> getConsultaVersion(@Query("id") @NotNull String id);

<<<<<<< Updated upstream
=======
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

>>>>>>> Stashed changes
}
