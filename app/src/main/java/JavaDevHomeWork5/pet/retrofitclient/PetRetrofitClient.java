package JavaDevHomeWork5.pet.retrofitclient;

import JavaDevHomeWork5.pet.model.Pet;
import JavaDevHomeWork5.responce.model.ApiResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PetRetrofitClient {
    @Multipart
    @POST("pet/{petId}/uploadImage")
    Call<ApiResponse> uploadImage (@Path("petId") long petId, @Part("additionalMetadata") RequestBody metadata, @Part MultipartBody.Part photo);

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

    @FormUrlEncoded
    @POST("pet/{petId}")
    Call<ApiResponse> updatePet (@Path("petId") long petId, @Field("name") String name, @Field("petStatus") String status);

    @DELETE("pet/{petId}")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> deletePet (@Path("petId") long petId);
}
