package JavaDevHomeWork5.pet.model;

import lombok.Getter;
@Getter
public enum PetStatus {
    AVAILABLE,
    PENDING,
    SOLD;

    public static String getNameInLowerCase(int index){
        return PetStatus.values()[index-1].name().toLowerCase();
    }
}
