package clasesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateEntregaDeMedicamentosModel {
    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("est")
    @Expose
    public String est;
    @SerializedName("fec")
    @Expose
    public int fec;
}
