package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class RecetaModel {
    @SerializedName("rme_codmdc")
    public int rme_codmdc;
    @SerializedName("rme_codcme")
    public int rme_codcme;
    @SerializedName("rme_indicaciones")
    public String rme_indicaciones;
    @SerializedName("rme_cantidad")
    public double rme_cantidad;
    @SerializedName("mdc_codigo")
    public int mdc_codigo;
    @SerializedName("mdc_nombre")
    public String mdc_nombre;
    @SerializedName("mdc_descripcion")
    public String mdc_descripcion;
    @SerializedName("mdc_medida")
    public String mdc_medida;
}
