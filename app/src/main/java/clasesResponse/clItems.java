package clasesResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class clItems implements Serializable{
    @SerializedName("items")
    public  List<clVersion> items;

    public class clVersion implements Serializable {
        @SerializedName("version_id")
        public String version_id;
        @SerializedName("version")
        public String version;
        @SerializedName("year")
        public String year;
    }
}
