package JavaDevHomeWork5.pet.model;

import lombok.Getter;
@Getter
public enum PetStatus {
    AVAILABLE ("available"),
    PENDING("pending"),
    SOLD ("sold");

    private final String nameOfStatus;

    PetStatus(String nameOfStatus) {
        this.nameOfStatus = nameOfStatus;
    }
}
