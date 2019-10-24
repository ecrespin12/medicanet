package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class HistorialModel {
    @SerializedName("hme_codigo")
    public String hme_codigo;
    @SerializedName("hem_codper")
    public String hme_codper;
    @SerializedName("hme_codthm")
    public String hme_codthm;
    @SerializedName("hme_descripcion")
    public String hme_descripcion;
    @SerializedName("hme_fecha_crea")
    public String hme_fecha_crea;
    @SerializedName("thm_nombre")
    public String thm_nombre;
}
