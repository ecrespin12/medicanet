package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class EntregaMedicamentoDetalleModel {
    @SerializedName("eme_codigo")
    public int eme_codigo;
    @SerializedName("ede_codigo")
    public int ede_codigo;
    @SerializedName("mdc_nombre")
    public String mdc_nombre;
    @SerializedName("rme_indicaciones")
    public String rme_indicaciones;
    @SerializedName("ede_cantidad")
    public int ede_cantidad;
    @SerializedName("ede_estado")
    public String ede_estado;
    @SerializedName("ede_fecha_entregado")
    public String ede_fecha_entregado;
}
