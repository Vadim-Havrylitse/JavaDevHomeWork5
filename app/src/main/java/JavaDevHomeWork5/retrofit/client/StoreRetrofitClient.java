package JavaDevHomeWork5.retrofit.client;

import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.store.model.Order;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

public interface StoreRetrofitClient {

    @POST("order/order")
    @Headers({"Content-Type: application/json"})
    Call<Order> placeAnOrder (@Body Order order);

    @GET("store/order/{orderId}")
    @Headers({"Content-Type: application/json"})
    Call<Order> getOrderById (@Path("orderId") long orderId);

    @DELETE("store/order/{orderId}")
    @Headers({"Content-Type: application/json"})
    Call<ApiResponse> deleteOrderById (@Path("orderId") long orderId);

    @GET("store/inventory")
    @Headers({"Content-Type: application/json"})
    Call<Map<String, Integer>> getPetInventoriesByStatus ();
}
