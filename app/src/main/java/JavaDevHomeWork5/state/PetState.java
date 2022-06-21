package JavaDevHomeWork5.state;

import JavaDevHomeWork5.App;
import JavaDevHomeWork5.pet.model.Pet;
import JavaDevHomeWork5.pet.service.PetService;
import JavaDevHomeWork5.responce.model.ApiResponse;

import java.io.IOException;
import java.util.List;

public class PetState extends State{
    private final PetService service;

    public PetState(App application) {
        super(application);
        service = new PetService(scanner);
    }

    @Override
    public void execute() {
        String[] message = {"Choose one of next operation with PET:"
                ,"press 1 -> Uploads an image"
                ,"press 2 -> Add a new pet to the store"
                ,"press 3 -> Update an existing pet"
                ,"press 4 -> Finds Pets by status"
                ,"press 5 -> Find pet by ID"
                ,"press 6 -> Update name and/or status of pet in the store"
                ,"press 7 -> Delete a pet"
                ,"press 0 -> MAIN MENU"};
        try {
            switch (checkCorrectInputAndReturnNumber(message)) {
                case 1:
                    ApiResponse apiResponse = service.addPhotoWithPet();
                    System.out.println("Result: " + apiResponse.getMessage());
                    break;
                case 2:
                    Pet pet = service.addNewPetToStore();
                    System.out.println("PET: \n" + pet.toString());
                    break;
                case 3:
                    Pet pet1 = service.updatePetInStore();
                    System.out.println("PET after update:\n"+pet1.toString());
                    break;
                case 4:
                    List<Pet> petsByStatus = service.findPetsByStatus();
                    petsByStatus.forEach(System.out::println);
                    break;
                case 5:
                    Pet petById = service.findPetById();
                    System.out.println("PET:\n" + petById.toString());
                    break;
                case 6:
                    ApiResponse apiResponse1 = service.updateNameAndStatusPet();
                    System.out.println("Result: " + apiResponse1.getMessage());
                    break;
                case 7:
                    ApiResponse apiResponse2 = service.deletePet();
                    System.out.println("Result: " + apiResponse2.getMessage());
                    break;
                case 0:
                    application.changeState(new MainState(application));
                    break;
            }
        } catch (IOException e) {
            printErrMessage(e);
        } finally {
            printUnderLines();
        }
    }
}
