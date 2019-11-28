package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class CentroMedicoModel {
    @SerializedName("cmd_codigo")
    public int cmd_codigo;
    @SerializedName("cmd_nombre")
    public String cmd_nombre;
    @SerializedName("cmd_latitud")
    public double cmd_latitud;
    @SerializedName("cmd_longitud")
    public double cmd_longitud;
}
