package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class PerfilPacienteModel {

    @SerializedName("per_nombre")
    public int per_nombre;
    @SerializedName("per_apellido")
    public String per_apellido;
    @SerializedName("per_fecha_nace")
    public String per_fecha_nace;
    @SerializedName("per_correo")
    public String per_correo;
    @SerializedName("per_estado")
    public String per_estado;
    @SerializedName("per_dui")
    public String per_dui;
}
