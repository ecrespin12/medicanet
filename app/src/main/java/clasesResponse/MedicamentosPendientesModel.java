package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class MedicamentosPendientesModel {
    @SerializedName("Paciente_Entrega")
    public String Paciente_Entrega;
    @SerializedName("Cantidad_Pendiente_Entrega")
    public Double Cantidad_Pendiente_Entrega;
    @SerializedName("Medicina")
    public String Medicina;
    @SerializedName("Indicaciones")
    public String Indicaciones;
}
