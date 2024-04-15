package ui;

import backend.forScanner.Scan;
import backend.model.User;
import backend.registratdiya.LogIn;
import backend.service.UserService;
import backend.service.UserServiceImpl;

import java.util.Objects;

public class MenuProfile {
    private static UserService userService = UserServiceImpl.getInstance();

    public static void profile() {
        System.out.println("Your Profile!!!");
        while (true){
            menu();
            Integer choose = Scan.scanInt("Choose");
            switch (choose){
                case 1-> editProfile();
                case 2-> showProfile();
                case 0->{
                    System.out.println("Bye Bye");
                    return;
                }
                default -> {
                    System.out.println("Error!!!");
                }
            }
        }

    }

    private static void showProfile() {
        for (User user : userService.get()) {
            if (Objects.equals(user.getId(), LogIn.getIdLogIn())){
                System.out.println(user);
            }
        }
    }

    private static void editProfile() {
        int index = 0;
        for (User user : userService.get()) {
            if (Objects.equals(user.getId(),LogIn.getIdLogIn())){
                break;
            }
            index=+1;
        }
        String name = Scan.scanStr("Enter Name");
        String username = Scan.scanStr("Enter Username");
        Integer phoneNumber = Scan.scanInt("Enter PhoneNumber");
        String password = Scan.scanStr("Enter Password");
        User user = new User(name, username, phoneNumber, password);
        LogIn.setIdLogIn(user.getId());
        userService.update(user,index);
    }

    private static void menu() {
        System.out.println("=========================================");
        System.out.println("""
                1. Edit profile
                2. Show Profile
                0. Exit
                """);
        System.out.println("=========================================");
    }
}
