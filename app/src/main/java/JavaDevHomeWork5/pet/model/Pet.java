package JavaDevHomeWork5.pet.model;

import JavaDevHomeWork5.utill.ModelService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

@Data
@Builder
public class Pet implements Serializable, ModelService {

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
    @SerializedName("petStatus")
    @Expose
    private String petStatus;

    @Override
    public String toString() {
        return new StringJoiner(", ", Pet.class.getSimpleName() + "[", "]")
                .add("id=" + formatOutputData(id))
                .add("category=" + formatOutputData(category))
                .add("petName='" + formatOutputData(petName) + "'")
                .add("photoUrls=" + formatOutputData(photoUrls))
                .add("tags=" + formatOutputData(tags))
                .add("petStatus=" + formatOutputData(petStatus))
                .toString();
    }

    private final static long serialVersionUID = 4321370639683641L;

}

