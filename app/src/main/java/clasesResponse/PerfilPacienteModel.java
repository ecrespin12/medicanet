package clasesResponse;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PerfilPacienteModel {

    @SerializedName("per_nombre")
    public String per_nombre;
    @SerializedName("per_apellidos")
    public String per_apellidos;
    @SerializedName("per_fecha_nace")
    public Date per_fecha_nace;
    @SerializedName("per_correo")
    public String per_correo;
    @SerializedName("per_estado")
    public String per_estado;
    @SerializedName("per_dui")
    public String per_dui;
}
