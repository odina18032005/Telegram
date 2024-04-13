package ui;

import backend.enums.TypeMessage;
import backend.forScanner.Scan;
import backend.model.Chat;
import backend.model.Group;
import backend.model.Message;
import backend.model.User;
import backend.registratdiya.LogIn;
import backend.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            System.out.println("""
                    Menu:
                    1. Edit Profile
                    2. View Contact
                    3. Open Chat
                    4. Open Group
                    5. Chats
                    6. Groups
                    7. Delete Chat
                    8. Delete Group
                    0. Exit
                    """);
            Integer choose = Scan.scanInt("Choose");
            switch (choose){
                case 1-> editProfile();
                case 2-> viewContact();
                case 3-> openChat();
                case 4-> openGroup();
                case 5-> chats();
                case 6-> groups();
                case 7-> deleteChat();
                case 8-> deleteGroup();
                case 0->{
                    System.out.println("Bye Bye");
                    return;
                }
                default -> System.out.println("Error!!!");
            }
        }
    }

    private static void deleteGroup() {

    }

    private static void deleteChat() {
        viewContact();
        Integer choose = Scan.scanInt("Choose");
        User user = UserServiceImpl.getUser(choose);
        if (user != null) {
            chatService.delete(user.getId());
            for (Chat chat : chatService.get()) {
                for (Message message : messageService.get()) {
                    if (Objects.equals(chat.getUserId2(),user.getId())&&
                            Objects.equals(chat.getId(),message.getChatId())
                    ){
                        messageService.delete(message.getId());
                    }
                }
            }
        }
    }

    private static void groups() {

    }

    private static void chats() {
        viewContact();
        Integer choose = Scan.scanInt("Choose");
        User user = UserServiceImpl.getUser(choose);
        if (user!=null){
            for (Chat chat : chatService.get()) {
                for (Message message : messageService.get()) {
                    if (Objects.equals(chat.getId(),message.getChatId())){
                        System.out.println(message);
                    }
                }
            }
            String text = Scan.scanStr("Enter Text");
            Message newMessage = new Message(text,LogIn.getIdLogIn(),TypeMessage.CHAT_MESSAGE,user);
            messageService.add(newMessage);
            chatService.add(new Chat(newMessage,user));
        }
    }

    private static void openGroup() {

    }

    private static void openChat() {
        viewContact();
        Integer choose = Scan.scanInt("Choose");
        User user = UserServiceImpl.getUser(choose);
        String text = Scan.scanStr("Enter Text");
        Message message = new Message(text,LogIn.getIdLogIn(),TypeMessage.CHAT_MESSAGE,user);
        messageService.add(message);
        assert user != null;
        chatService.add(new Chat(message, user));

    }

    private static void viewContact() {
        List<User> users = userService.get();
        int i = 1;
        for (User user : users) {
            if(!Objects.equals(user.getId(),LogIn.getIdLogIn())){
                System.out.println(i + ". " + user.getName());
                i++;
            }
        }
    }

    private static void editProfile() {
        String name = Scan.scanStr("Enter Name");
        String username = Scan.scanStr("Enter Username");
        Integer phoneNumber = Scan.scanInt("Enter PhoneNumber");
        String password = Scan.scanStr("Enter Password");
        userService.update(new User(name, username, phoneNumber, password));
    }
}