package JavaDevHomeWork5.retrofit.client;

import JavaDevHomeWork5.pet.model.Pet;
import JavaDevHomeWork5.pet.model.PetStatus;
import JavaDevHomeWork5.responce.model.ApiResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PetRetrofitClient {
    @POST("pet/{petId}/uploadImage")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> uploadImage (@Path("petId") long petId, @Body RequestBody fileToUpload);

    @POST("pet")
    @Headers({"Content-Type: application/json"})
    Call<Pet> addPetToStore (@Body Pet pet);

    @PUT("pet")
    @Headers({"Content-Type: application/json"})
    Call<Pet> updatePet (@Body Pet pet);

    @GET("pet/findByStatus")
    @Headers({"Content-Type: application/json"})
    Call<List<Pet>> findByStatus (@Query("status") String[] petStatuses);

    @GET("pet/{petId}")
    @Headers({"Content-Type: application/json"})
    Call<Pet> getPetById (@Path("petId") long petId);

    @POST("pet/{petId}")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> updatePet (@Path("petId") long petId, @Field("name") String name, @Field("status") String status);

    @DELETE("pet/{petId}")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> deletePet (@Path("petId") long petId);
}
