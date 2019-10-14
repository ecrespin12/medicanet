package clasesResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class clVersion implements Serializable {
    @SerializedName("version_id")
    public String version_id;
    @SerializedName("version")
    public String version;
    @SerializedName("year")
    public String year;
}
