package JavaDevHomeWork5.state;

import JavaDevHomeWork5.App;
import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.user.model.User;
import JavaDevHomeWork5.user.service.UserService;

import java.io.IOException;

public class UserState extends State{
    private final UserService service;

    public UserState(App application) {
        super(application);
        this.service = new UserService(scanner);
    }

    @Override
    public void execute() {
        String[] message = {"Choose one of next operation with USER:"
                ,"press 1 -> Creates array of users"
                ,"press 2 -> Creates List of users"
                ,"press 3 -> Get user by user name"
                ,"press 4 -> Updated user"
                ,"press 5 -> Delete user"
                ,"press 6 -> Logs user into the system"
                ,"press 7 -> Logs out current logged in user session"
                ,"press 8 -> Create user"
                ,"press 0 -> MAIN MENU"};
        try {
            switch (checkCorrectInputAndReturnNumber(message)) {
                case 1:
                    ApiResponse response = service.createUsersArrayAndSendPostRequest();
                    System.out.println("Result: " + response.getMessage());
                    break;
                case 2:
                    ApiResponse response1 = service.createUsersListAndSendPostRequest();
                    System.out.println("Result: " + response1.getMessage());
                    break;
                case 3:
                    User userFromApi = service.getUserFromApi();
                    System.out.println(userFromApi.toString());
                    break;
                case 4:
                    ApiResponse response2 = service.updateUser();
                    System.out.println("Result: " + response2.getMessage());
                    break;
                case 5:
                    ApiResponse response3 = service.deleteUser();
                    System.out.println("Result: " + response3.getMessage());
                    break;
                case 6:
                    ApiResponse response4 = service.loginUser();
                    System.out.println("Result: " + response4.getMessage());
                    break;
                case 7:
                    ApiResponse response5 = service.logout();
                    System.out.println("Result: " + response5.getMessage());
                    break;
                case 8:
                    ApiResponse response6 = service.createUserAndSendPostRequest();
                    System.out.println("Result: " + response6.getMessage());
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
