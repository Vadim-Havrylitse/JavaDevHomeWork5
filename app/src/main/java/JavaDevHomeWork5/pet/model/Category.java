package JavaDevHomeWork5.pet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class Category implements Serializable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;

    private final static long serialVersionUID = -5105955409634159609L;

}
