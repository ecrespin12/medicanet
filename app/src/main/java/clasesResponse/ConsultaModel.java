package clasesResponse;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ConsultaModel {
    @SerializedName("cme_codigo")
    public String cme_codigo;
    @SerializedName("per_nombre")
    public String per_nombre;
    @SerializedName("per_correo")
    public String per_correo;
    @SerializedName("per_dui")
    public String per_dui;
    @SerializedName("per_fecha_nace")
    public String per_fecha_nace;
    @SerializedName("med_nombre")
    public String med_nombre;
    @SerializedName("med_correo")
    public String med_correo;
    @SerializedName("cmd_codigo")
    public String cmd_codigo;
    @SerializedName("cmd_nombre")
    public String cmd_nombre;
    @SerializedName("cmd_latitud")
    public String cmd_latitud;
    @SerializedName("cmd_longitud")
    public String cmd_longitud;
    @SerializedName("cme_fecha_hora")
    public String cme_fecha_hora;
}
