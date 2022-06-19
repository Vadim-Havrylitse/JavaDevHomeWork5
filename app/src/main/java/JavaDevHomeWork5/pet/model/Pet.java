package JavaDevHomeWork5.pet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Pet implements Serializable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("name")
    @Expose
    private String petName;
    @SerializedName("photoUrls")
    @Expose
    private List<String> photoUrls;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags;
    @SerializedName("status")
    @Expose
    private PetStatus status;

    private final static long serialVersionUID = 4321370639683641L;

}

