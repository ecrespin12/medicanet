package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class MedicamentosModel {
    @SerializedName("mdc_codigo")
    public int mdc_codigo;
    @SerializedName("mdc_nombre")
    public String mdc_nombre;
    @SerializedName("mdc_descripcion")
    public String mdc_descripcion;
    @SerializedName("mdc_medida")
    public String mdc_medida;
}
