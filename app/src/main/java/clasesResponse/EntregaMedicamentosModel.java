package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class EntregaMedicamentosModel {

    @SerializedName("eme_codigo")
    public int eme_codigo;
    @SerializedName("eme_codcme")
    public Double eme_codcme;
    @SerializedName("far_codigo")
    public Double far_codigo;
    @SerializedName("far_nombre")
    public String far_nombre;
    @SerializedName("far_latitud")
    public String far_latitud;
    @SerializedName("far_longitud")
    public String far_longitud;
    @SerializedName("med_codigo")
    public Double med_codigo;
    @SerializedName("med_nombre")
    public String med_nombre;
    @SerializedName("pac_codigo")
    public Double pac_codigo;
    @SerializedName("pac_nombre")
    public String pac_nombre;
    @SerializedName("eme_fecha_entrega")
    public String eme_fecha_entrega;
    @SerializedName("eme_estado")
    public String eme_estado;
}
