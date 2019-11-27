package clasesResponse;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PacientesModel {
    @SerializedName("med_nombre")
    public String med_nombre;
    @SerializedName("per_codigo")
    public int per_codigo;
    @SerializedName("pac_nombre")
    public String pac_nombre;
    @SerializedName("per_dui")
    public String per_dui;
    @SerializedName("per_correo")
    public String per_correo;
    @SerializedName("per_fecha_nace")
    public Date per_fecha_nace;
    @SerializedName("per_estado")
    public String per_estado;
}
