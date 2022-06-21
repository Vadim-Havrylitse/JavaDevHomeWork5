package JavaDevHomeWork5.order.service;

import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.retrofit.RetrofitService;
import JavaDevHomeWork5.order.retrofitclient.OrderRetrofitClient;
import JavaDevHomeWork5.order.model.Order;
import JavaDevHomeWork5.order.model.OrderStatus;
import JavaDevHomeWork5.utill.IdService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class OrderService implements IdService {
    public static final OrderRetrofitClient retrofitService = RetrofitService.getRetrofitService(OrderRetrofitClient.class);
    private final Scanner scanner;

    public OrderService(Scanner scanner) {
        this.scanner = scanner;
    }


    public Order createNewOrderAndSendPostRequest () throws IOException {
        long id;
        long petId;
        int quantity;
        String shipDate;
        OrderStatus status;
        boolean complete;
        try {
            id = getIDFromCommandLine(scanner);
            System.out.println("Write PET_ID(long):");
            petId = Long.parseLong(scanner.nextLine());
            System.out.println("Write QUANTITY(int):");
            quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Write date?\n"
                    + "press1 -> write you date\n"
                    + "press2 -> generate actual date");
            if (scanner.nextLine().equals("1")) {
                System.out.println("Write DATE(yyyy-mm-ddThh:ss:ms) or (yyyy-mm-dd):");
                shipDate = scanner.nextLine();
            } else {
                shipDate = String.valueOf(LocalDate.now());
            }
            System.out.println("Choose number of ORDER_STATUS(1,2,3):\n"
                    + Arrays.toString(OrderStatus.values()));
            int inputNumberOfStatus = Integer.parseInt(scanner.nextLine());
            status = OrderStatus.values()[inputNumberOfStatus - 1];
            System.out.println("Order COMPLETE?\n"
                    + "press1 -> true\n"
                    + "press2 -> false");
            complete = scanner.nextLine().equals("1");

            Order order = Order.builder()
                    .id(id)
                    .status(status)
                    .quantity(quantity)
                    .complete(complete)
                    .petId(petId)
                    .shipDate(shipDate)
                    .build();
            return RetrofitService.cheekResponseCall(retrofitService.placeAnOrder(order));
        }catch (RuntimeException|IOException e){
            throw new IOException(e);
        }
    }

    public Order findOrderById () throws IOException {
        long orderId = getIDFromCommandLine(scanner);
        return RetrofitService.cheekResponseCall(retrofitService.getOrderById(orderId));
    }

    public ApiResponse deleteOrderById () throws IOException {
        long orderId = getIDFromCommandLine(scanner);
        return RetrofitService.cheekResponseCall(retrofitService.deleteOrderById(orderId));
    }

    public Map<String,Integer> getPetInventories() throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.getPetInventoriesByStatus());
    }
}
