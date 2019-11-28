package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class MedicamentosPendientesModel {
    @SerializedName("per_codigo")
    public int per_codigo;
    @SerializedName("med_nombre")
    public String med_nombre;
    @SerializedName("mdc_nombre")
    public String mdc_nombre;
    @SerializedName("mdc_descripcion")
    public String mdc_descripcion;
    @SerializedName("rme_cantidad")
    public String rme_cantidad;
    @SerializedName("rme_indicaciones")
    public String rme_indicaciones;
}
