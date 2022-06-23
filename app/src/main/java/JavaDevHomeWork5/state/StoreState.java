package JavaDevHomeWork5.state;

import JavaDevHomeWork5.App;
import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.order.model.Order;
import JavaDevHomeWork5.order.service.OrderService;

import java.io.IOException;
import java.util.Map;

public class StoreState extends State{
    public final OrderService service;

    public StoreState(App application) {
        super(application);
        this.service = new OrderService(scanner);
    }
    @Override
    public void execute() {
        String[] message = {"Choose one of next operation with STORE:"
                ,"press 1 -> Place an order for a pet"
                ,"press 2 -> Find purchase order by ID"
                ,"press 3 -> Delete purchase order by ID"
                ,"press 4 -> Returns pet inventories by petStatus"
                ,"press 0 -> MAIN MENU"};
        try {
            switch (checkCorrectInputAndReturnNumber(message)) {
                case 1:
                    Order responseOrder = service.createNewOrderAndSendPostRequest();
                    System.out.println(responseOrder.toString());
                    break;
                case 2:
                    Order orderById = service.findOrderById();
                    System.out.println(orderById.toString());
                    break;
                case 3:
                    ApiResponse apiResponse = service.deleteOrderById();
                    System.out.println("Result: "+apiResponse.getMessage());
                    break;
                case 4:
                    Map<String, Integer> petInventories = service.getPetInventories();
                    petInventories.forEach((key, value) -> System.out.println("\t\t"+key+" : "+value));
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
