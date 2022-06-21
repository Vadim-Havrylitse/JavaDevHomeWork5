package JavaDevHomeWork5.order.retrofitclient;

import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.order.model.Order;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface OrderRetrofitClient {

    @POST("store/order")
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
