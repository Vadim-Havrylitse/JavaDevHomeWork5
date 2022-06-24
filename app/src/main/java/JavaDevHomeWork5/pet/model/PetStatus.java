package JavaDevHomeWork5.pet.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.io.Serializable;

@Getter
public enum PetStatus implements Serializable{
    @SerializedName("available")
    AVAILABLE,
    @SerializedName("pending")
    PENDING,
    @SerializedName("sold")
    SOLD;

    public static String getNameInLowerCase(int index){
        return PetStatus.values()[index-1].name().toLowerCase();
    }

    public static PetStatus getByIndex(int index){
        return PetStatus.values()[index-1];
    }
}
