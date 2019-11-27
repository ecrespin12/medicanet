package clasesResponse;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HistorialModel {
    @SerializedName("hme_codigo")
    public int hme_codigo;
    @SerializedName("hme_codthm")
    public int hme_codthm;
    @SerializedName("thm_nombre")
    public String thm_nombre;
    @SerializedName("hme_descripcion")
    public String hme_descripcion;
    @SerializedName("hme_fecha_crea")
    public Date hme_fecha_crea;

}
