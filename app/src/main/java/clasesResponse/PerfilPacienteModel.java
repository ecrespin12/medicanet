package clasesResponse;

import com.google.gson.annotations.SerializedName;

public class PerfilPacienteModel {

    @SerializedName("cod")
    public int cod;
    @SerializedName("nom")
    public String nom;
    @SerializedName("fec")
    public String fed;
    @SerializedName("cor")
    public String cor;
    @SerializedName("est")
    public String est;
    @SerializedName("dui")
    public String dui;
}
