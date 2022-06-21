package JavaDevHomeWork5.user.retrofitclient;

import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.user.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserRetrofitClient {

    @POST("user/createWithArray")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> addUsersFromArray (@Body User[] usersArray);

    @POST("user/createWithList")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> addUsersFromList (@Body List<User> usersListJson);

    @GET("user/{username}")
    @Headers({"Content-Type: application/json"})
    Call<User> getUserByUsername (@Path("username") String username);

    @PUT("user/{username}")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> updateUser (@Path("username") String username, @Body User user);

    @DELETE("user/{username}")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> deleteUser (@Path("username") String username);

    @GET("user/login")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> userLogin (@Query("username") String username, @Query("password") String password);

    @GET("user/logout")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> userLogout ();

    @POST("user")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> addUser (@Body User user);
}
