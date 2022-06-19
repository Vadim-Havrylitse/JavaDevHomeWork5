package JavaDevHomeWork5.responce.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ApiResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    public int code;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("message")
    @Expose
    public String message;

    private final static long serialVersionUID = 3350422129964394466L;

}
