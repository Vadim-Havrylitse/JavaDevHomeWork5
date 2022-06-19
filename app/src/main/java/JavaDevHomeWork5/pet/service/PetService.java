package JavaDevHomeWork5.pet.service;

import JavaDevHomeWork5.pet.model.Category;
import JavaDevHomeWork5.pet.model.Pet;
import JavaDevHomeWork5.pet.model.PetStatus;
import JavaDevHomeWork5.pet.model.Tag;
import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.retrofit.RetrofitService;
import JavaDevHomeWork5.retrofit.client.PetRetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService {
    public static final PetRetrofitClient retrofitService= RetrofitService.getRetrofitService(PetRetrofitClient.class);
    private final Scanner scanner;

    public PetService(Scanner scanner) {
        this.scanner = scanner;
    }

    public Pet addNewPetToStore () throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.addPetToStore(createPet()));
    }

    public Pet updatePetInStore () throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.updatePet(createPet()));
    }

    public List<Pet> findPetsByStatus () throws IOException {
        List<String> array = new ArrayList<>();
        System.out.println("Choose number of PET_STATUS(multiselect, for example 12,13,123,3 or 2 etc.):\n"
                + Arrays.toString(PetStatus.values()));
        for (char numberOfStatus : scanner.nextLine().toCharArray()) {
            int index = Character.getNumericValue(numberOfStatus)-1;
            array.add(PetStatus.values()[index].getNameOfStatus());
        }
        String[] filterByStatus = new String[array.size()];
        array.toArray(filterByStatus);
        return RetrofitService.cheekResponseCall(retrofitService.findByStatus(filterByStatus));
    }

    public Pet findPetById () throws IOException {
        System.out.println("Write ID(long):");
        long id = scanner.nextLong();
        return RetrofitService.cheekResponseCall(retrofitService.getPetById(id));
    }

    public ApiResponse updatePet() throws IOException {
        System.out.println("Write ID(long)");
        long id = scanner.nextLong();
        System.out.println("Write PET_NAME for update:");
        String petName = scanner.nextLine();
        System.out.println("Choose number of PET_STATUS(1,2,3) for update:\n"
                + Arrays.toString(PetStatus.values()));
        PetStatus status = PetStatus.values()[scanner.nextInt()-1];
        return RetrofitService.cheekResponseCall(retrofitService.updatePet(id,petName,status.getNameOfStatus()));
    }

    public ApiResponse deletePet () throws IOException {
        System.out.println("Write ID(long):");
        long id = scanner.nextLong();
        return RetrofitService.cheekResponseCall(retrofitService.deletePet(id));
    }

    private Pet createPet(){
        long id;
        Category category = new Category();
        String petName;
        List<String> photoUrls = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        PetStatus status;

        System.out.println("Write ID(long):");
        id = scanner.nextLong();
        System.out.println("Write PET_NAME:");
        petName = scanner.nextLine();
        System.out.println("Write CATEGORY_ID(long):");
        category.setId(scanner.nextLong());
        System.out.println("Write CATEGORY_NAME:");
        category.setName(scanner.nextLine());
        System.out.println("How many tags are you want to add?");
        for (int i = 0; i < scanner.nextInt(); i++) {
            Tag tag = new Tag();
            System.out.println("Write TAG_ID(long):");
            tag.setId(scanner.nextLong());
            System.out.println("Write TAG_NAME:");
            tag.setName(scanner.nextLine());
            tags.add(tag);
        }
        System.out.println("How many URL of photo are you want to add?");
        for (int i = 0; i < scanner.nextInt(); i++) {
            System.out.println("Add PHOTO_URL:");
            photoUrls.add(scanner.nextLine());
        }
        System.out.println("Choose number of PET_STATUS(1,2,3):\n"
                + Arrays.toString(PetStatus.values()));
        status = PetStatus.values()[scanner.nextInt()-1];
        return Pet.builder()
                .id(id)
                .petName(petName)
                .status(status)
                .photoUrls(photoUrls)
                .category(category)
                .tags(tags)
                .build();
    }



}
