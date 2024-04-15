package ui;

import backend.enums.TypeMessage;
import backend.forScanner.Scan;
import backend.model.Chat;
import backend.model.Group;
import backend.model.Message;
import backend.model.User;
import backend.registratdiya.LogIn;
import backend.service.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static backend.registratdiya.LogIn.menuLogIn;

public class FrontEnd {
    private static UserService userService = UserServiceImpl.getInstance();
    private static ChatService chatService = ChatServiceImpl.getInstance();
    private static GroupService groupService = GroupServiceImpl.getInstance();
    private static MessageService messageService = MessageServiceImpl.getInstance();

    public static void main(String[] args) {
        System.out.println("Welcome in Telegram");
        while (true){
            Integer menuLogIn = menuLogIn();
            switch (menuLogIn){
                case 1-> {
                    boolean logIn = LogIn.logIn();
                    if (logIn){
                        menu();
                    }else {
                        System.out.println("Error");
                    }
                }
                case 2-> {
                    User signUp = LogIn.signUp();
                    if (signUp!=null){
                        menu();
                    }else {
                        System.out.println("Error");
                    }
                }
                case 0-> {
                    System.out.println("Bye Bye");
                    return;
                }
                default -> System.out.println("Error!!!");
            }
        }

    }

    private static void menu() {
        while (true){
            System.out.println("=========================================");
            System.out.println("""
                    Menu:
                    1. Profile
                    2. Chats
                    3. Group
                    0. Exit
                    """);
            System.out.println("=========================================");
            Integer choose = Scan.scanInt("Choose");
            switch (choose){
                case 1-> menuProfile();
                case 2-> menuChats();
                case 3-> menuGroups();
                case 0->{
                    System.out.println("Bye Bye");
                    return;
                }
                default -> System.out.println("Error!!!");
            }
        }
    }

    private static void menuGroups() {
        MenuGroups.group();
    }

    private static void menuChats() {
        MenuChats.chat();
    }

    private static void search() {
        String search = Scan.scanStr("Search");
        for (User user : userService.get()) {
            if (Objects.equals(user.getName(),search)||Objects.equals(user.getUsername(),search)||Objects.equals(user.getPhoneNumber(),search)){
                System.out.println(user);
            }
        }
    }

    public static void viewContact() {
        List<User> users = userService.get();
        int i = 1;
        for (User user : users) {
            if(!Objects.equals(user.getId(),LogIn.getIdLogIn())){
                System.out.println(i + ". " + user.getName());
                i++;
            }
        }
    }

    private static void menuProfile() {
        MenuProfile.profile();
    }
}