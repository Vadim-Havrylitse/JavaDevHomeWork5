package JavaDevHomeWork5.pet.service;

import JavaDevHomeWork5.pet.model.Category;
import JavaDevHomeWork5.pet.model.Pet;
import JavaDevHomeWork5.pet.model.PetStatus;
import JavaDevHomeWork5.pet.model.Tag;
import JavaDevHomeWork5.pet.retrofitclient.PetRetrofitClient;
import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.retrofit.RetrofitService;
import JavaDevHomeWork5.utill.IdService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PetService implements IdService {
    public static final PetRetrofitClient retrofitService = RetrofitService.getRetrofitService(PetRetrofitClient.class);
    private final Scanner scanner;

    public PetService(Scanner scanner) {
        this.scanner = scanner;
    }

    public ApiResponse addPhotoWithPet () throws IOException {
        try {
            long id = getIDFromCommandLine(scanner);
            System.out.println("Write additional data for photo to pass to server (may be empty)(may be empty):");
            String metadataString = scanner.nextLine();

            System.out.println("Write path to PHOTO on you device (C:\\***\\filename.jpeg):");
            File inputFile = new File(scanner.nextLine());

            MultipartBody.Part photo = MultipartBody.Part.createFormData(
                    "file",
                    inputFile.getName(),
                    RequestBody.create(inputFile, MediaType.parse("image/*")));
            RequestBody additionalMetadata = RequestBody.create(metadataString, MediaType.parse("text/plain"));

            return RetrofitService.cheekResponseCall(retrofitService.uploadImage(id, additionalMetadata, photo));
        }catch (FileNotFoundException | IllegalArgumentException e){
            throw new IOException("Your image not found, look at example and try again.");
        }
    }

    public Pet addNewPetToStore () throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.addPetToStore(createPet()));
    }

    public Pet updatePetInStore () throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.updatePet(createPet()));
    }

    public List<Pet> findPetsByStatus () throws IOException {
        List<String> array = new ArrayList<>();
        System.out.println("Choose number of PET_STATUS(multiselect, for example 12,13,123,3 etc.):\n"
                + Arrays.toString(PetStatus.values()));
        for (char numberOfStatus : scanner.nextLine().toCharArray()) {
            int index = Character.getNumericValue(numberOfStatus);
            array.add(PetStatus.getNameInLowerCase(index));
        }
        String[] filterByStatus = new String[array.size()];
        array.toArray(filterByStatus);
        return RetrofitService.cheekResponseCall(retrofitService.findByStatus(filterByStatus));
    }

    public Pet findPetById () throws IOException {
        long id = getIDFromCommandLine(scanner);
        return RetrofitService.cheekResponseCall(retrofitService.getPetById(id));
    }

    public ApiResponse updateNameAndStatusPet() throws IOException {
        long id = getIDFromCommandLine(scanner);
        System.out.println("Write PET_NAME for update:");
        String petName = scanner.nextLine();
        System.out.println("Choose number of PET_STATUS(1,2,3) for update:\n"
                + Arrays.toString(PetStatus.values()));
        int index = Integer.parseInt(scanner.nextLine());
        String statusInLowerCase = PetStatus.getNameInLowerCase(index);
        return RetrofitService.cheekResponseCall(retrofitService.updatePet(id, petName, statusInLowerCase));
    }

    public ApiResponse deletePet () throws IOException {
        long id = getIDFromCommandLine(scanner);
        return RetrofitService.cheekResponseCall(retrofitService.deletePet(id));
    }

    private Pet createPet() throws IOException {
        long id;
        Category category = new Category();
        String petName;
        List<String> photoUrls = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        String status;

        try {
            id = getIDFromCommandLine(scanner);
            System.out.println("Write PET_NAME:");
            petName = scanner.nextLine();
            System.out.println("Write CATEGORY_ID(long):");
            category.setId(Long.parseLong(scanner.nextLine()));
            System.out.println("Write CATEGORY_NAME:");
            category.setName(scanner.nextLine());
            System.out.println("How many tags are you want to add? (may be 0)");
            int index = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < index; i++) {
                Tag tag = new Tag();
                System.out.println("Write TAG_ID(long):");
                tag.setId(Long.parseLong(scanner.nextLine()));
                System.out.println("Write TAG_NAME:");
                tag.setName(scanner.nextLine());
                tags.add(tag);
            }
            System.out.println("How many URL of photo are you want to add? (may be 0)");
            index = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < index; i++) {
                System.out.println("Add PHOTO_URL:");
                photoUrls.add(scanner.nextLine());
            }
            System.out.println("Choose number of PET_STATUS(1,2,3):\n"
                    + Arrays.toString(PetStatus.values()));
            index = Integer.parseInt(scanner.nextLine());
            status = PetStatus.getNameInLowerCase(index);
            return Pet.builder()
                    .id(id)
                    .petName(petName)
                    .petStatus(status)
                    .photoUrls(photoUrls)
                    .category(category)
                    .tags(tags)
                    .build();
        }catch (IOException|RuntimeException e){
            throw new IOException(e);
        }
    }
}
