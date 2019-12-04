package clasesResponse;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ConsultaModel {
    @SerializedName("cme_codigo")
    public int cme_codigo;
    @SerializedName("cme_codper")
    public int cme_codper;
    @SerializedName("per_nombre")
    public String per_nombre;
    @SerializedName("per_correo")
    public String per_correo;
    @SerializedName("per_dui")
    public String per_dui;
    @SerializedName("per_fecha_nace")
    public Date per_fecha_nace;
    @SerializedName("med_codigo")
    public String med_codigo;
    @SerializedName("med_nombre")
    public String med_nombre;
    @SerializedName("med_correo")
    public String med_correo;
    @SerializedName("cmd_codigo")
    public int cmd_codigo;
    @SerializedName("cmd_nombre")
    public String cmd_nombre;
    @SerializedName("cmd_latitud")
    public double cmd_latitud;
    @SerializedName("cmd_longitud")
    public double cmd_longitud;
    @SerializedName("cme_fecha_hora")
    public Date cme_fecha_hora;
}
