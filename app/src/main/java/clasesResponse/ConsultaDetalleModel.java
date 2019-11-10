package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class ConsultaDetalleModel {
    @SerializedName("dcm_codigo")
    public int dcm_codigo;
    @SerializedName("dcm_nombre")
    public String dcm_nombre;
    @SerializedName("dcm_descripcion")
    public String dcm_descripcion;
}
