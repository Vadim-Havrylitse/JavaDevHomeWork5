package JavaDevHomeWork5.store.service;

import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.retrofit.RetrofitService;
import JavaDevHomeWork5.retrofit.client.StoreRetrofitClient;
import JavaDevHomeWork5.store.model.Order;
import JavaDevHomeWork5.store.model.OrderStatus;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class StoreService {
    public static final StoreRetrofitClient retrofitService= RetrofitService.getRetrofitService(StoreRetrofitClient.class);
    private final Scanner scanner;

    public StoreService(Scanner scanner) {
        this.scanner = scanner;
    }

    public Order createNewOrderAndSendPostRequest () throws IOException {
        long id;
        long petId;
        int quantity;
        String shipDate;
        OrderStatus status;
        boolean complete;

        System.out.println("Write ID(long):");
        id = scanner.nextLong();
        System.out.println("Write PET_ID(long):");
        petId = scanner.nextLong();
        System.out.println("Write QUANTITY(int):");
        quantity = scanner.nextInt();
        System.out.println("Write date?\n"
                +"press1 -> write you date\n"
                +"press2 -> generate actual date");
        if (scanner.nextByte() == 1){
            System.out.println("Write DATE(yyyy-mm-ddThh:ss:ms)/(yyyy-mm-dd):");
            shipDate = scanner.nextLine();
        } else {
            shipDate = LocalDate.now().toString();
        }
        System.out.println("Choose number of ORDER_STATUS(1,2,3):\n"
                + Arrays.toString(OrderStatus.values()));
        status = OrderStatus.values()[scanner.nextInt()-1];
        System.out.println("Order COMPLETE(true/false)?");
        complete = scanner.nextBoolean();

        Order order = Order.builder()
                .id(id)
                .status(status)
                .quantity(quantity)
                .complete(complete)
                .petId(petId)
                .shipDate(shipDate)
                .build();
        return RetrofitService.cheekResponseCall(retrofitService.placeAnOrder(order));
    }

    public Order findOrderById () throws IOException {
        System.out.println("Write ID:");
        long orderId = scanner.nextLong();
        return RetrofitService.cheekResponseCall(retrofitService.getOrderById(orderId));
    }

    public ApiResponse deleteOrderById () throws IOException {
        System.out.println("Write ID:");
        long orderId = scanner.nextLong();
        return RetrofitService.cheekResponseCall(retrofitService.deleteOrderById(orderId));
    }

    public Map<String,Integer> getPetInventories() throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.getPetInventoriesByStatus());
    }






}
