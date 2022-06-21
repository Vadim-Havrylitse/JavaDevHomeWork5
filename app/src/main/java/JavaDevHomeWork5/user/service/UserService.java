package JavaDevHomeWork5.user.service;

import JavaDevHomeWork5.responce.model.ApiResponse;
import JavaDevHomeWork5.retrofit.RetrofitService;
import JavaDevHomeWork5.user.retrofitclient.UserRetrofitClient;
import JavaDevHomeWork5.user.model.User;
import JavaDevHomeWork5.utill.IdService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements IdService {
    private static final UserRetrofitClient retrofitService = RetrofitService.getRetrofitService(UserRetrofitClient.class);
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }

    public ApiResponse createUserAndSendPostRequest () throws IOException {
        User createdUser = createUser();
        return RetrofitService.cheekResponseCall(retrofitService.addUser(createdUser));
    }

    public ApiResponse createUsersArrayAndSendPostRequest () throws IOException {
        System.out.println("How many users are you want create?");
        int amountOfUsers = Integer.parseInt(scanner.nextLine());
        User[] userArray = new User[amountOfUsers];

        for (int i = 0; i < amountOfUsers; i++){
            userArray[i] = createUser();
        }
        return RetrofitService.cheekResponseCall(retrofitService.addUsersFromArray(userArray));
    }

    public ApiResponse createUsersListAndSendPostRequest () throws IOException {
        System.out.println("How many users are you want create?");
        int amountOfUsers = Integer.parseInt(scanner.nextLine());
        List<User> userList = new ArrayList<>(amountOfUsers);

        for (int i = 0; i < amountOfUsers; i++){
            userList.add(createUser());
        }
        return RetrofitService.cheekResponseCall(retrofitService.addUsersFromList(userList));
    }

    public User getUserFromApi() throws IOException {
        System.out.println("Write USERNAME:");
        String username = scanner.nextLine();
        return RetrofitService.cheekResponseCall(retrofitService.getUserByUsername(username));
    }

    public ApiResponse updateUser() throws IOException {
        User userForUpdate = createUser();
        return RetrofitService.cheekResponseCall(retrofitService.updateUser(userForUpdate.getUsername(), userForUpdate));
    }

    public ApiResponse deleteUser() throws IOException {
        System.out.println("Write USERNAME:");
        String username = scanner.nextLine();
        return RetrofitService.cheekResponseCall(retrofitService.deleteUser(username));
    }

    public ApiResponse loginUser() throws IOException {
        System.out.println("Write USERNAME:");
        String username = scanner.nextLine();
        System.out.println("Write PASSWORD:");
        String password = scanner.nextLine();
        return RetrofitService.cheekResponseCall(retrofitService.userLogin(username, password));
    }

    public ApiResponse logout() throws IOException {
        return RetrofitService.cheekResponseCall(retrofitService.userLogout());
    }


    private User createUser () throws IOException {
        long id;
        String firstName;
        String lastName;
        String email;
        String username;
        String password;
        String phone;
        int userStatus;
        try {
            id = getIDFromCommandLine(scanner);
            System.out.println("Write user FIRSTNAME:");
            firstName = scanner.nextLine();
            System.out.println("Write user LASTNAME:");
            lastName = scanner.nextLine();
            System.out.println("Write user EMAIL:");
            email = scanner.nextLine();
            System.out.println("Write user PHONE:");
            phone = scanner.nextLine();
            System.out.println("Write user USERNAME:");
            username = scanner.nextLine();
            System.out.println("Write user PASSWORD:");
            password = scanner.nextLine();
            System.out.println("Write user USER_STATUS(int):");
            userStatus = Integer.parseInt(scanner.nextLine());
            return User.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .phone(phone)
                    .username(username)
                    .password(password)
                    .userStatus(userStatus)
                    .build();
        }catch (RuntimeException|IOException e){
            throw new IOException(e);
        }
    }
}
