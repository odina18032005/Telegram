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
                    9. Search
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
                case 9-> search();
                case 0->{
                    System.out.println("Bye Bye");
                    return;
                }
                default -> System.out.println("Error!!!");
            }
        }
    }

    private static void search() {
        String search = Scan.scanStr("Search");
        for (User user : userService.get()) {
            if (Objects.equals(user.getName(),search)||Objects.equals(user.getUsername(),search)||Objects.equals(user.getPhoneNumber(),search)){
                System.out.println(user);
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
                if(chat!=null){
                    for (Message message : messageService.get()) {
                        if (message!=null){
                            if (Objects.equals(chat.getUserId2(),user.getId())&&
                                    Objects.equals(chat.getId(),message.getChatId())
                            ){
                                messageService.delete(message.getId());
                            }
                        }
                        else{
                            System.out.println("No messsages yet!!!");
                        }
                    }
                }
            }
        }
    }

    private static void groups() {

        int i = 1;
        for (Group group : groupService.get()) {
            if (group!=null ){
                System.out.println(i + ". " + group.getName());
                i++;
            }
        }
        Integer choose = Scan.scanInt("Choose") - 1;
        Group group = groupService.getGroup(choose);
        String groupId = "";
        String userId = "";
        String name = "";
        if (group != null) {
            System.out.println("                          " + group.getName());
            for (Group group1 : groupService.get()) {
                for (Message message : messageService.get()) {
                    if (Objects.equals(group1.getId(), message.getChatId())) {
                        System.out.println(message);
                        groupId = group.getId();
                        userId = group.getUserId();
                        name = group.getName();
                    }
                }
            }
        }
        String text = Scan.scanStr("Enter Text");
        Message newMessage = new Message(text,LogIn.getIdLogIn(),TypeMessage.GROUP_MESSAGE, userId, groupId);
        messageService.add(newMessage);
    }

    private static void chats() {
        viewContact();
        Integer choose = Scan.scanInt("Choose");
        User user = UserServiceImpl.getUser(choose);
        String chatId = "";
        if (user!=null){
            for (Chat chat : chatService.get()) {
                if (chat!=null){
                    for (Message message : messageService.get()) {
                        if (message!=null){
                            if (Objects.equals(chat.getId(),message.getChatId())&&
                                    Objects.equals(chat.getUserId2(),user.getId())
                            ){
                                System.out.println(message);
                                chatId = chat.getId();
                            }
                        }else{
                            System.out.println("No messsages yet!!!");
                        }
                    }
                }
            }
            String text = Scan.scanStr("Enter Text");
            Message newMessage = new Message(text,LogIn.getIdLogIn(),TypeMessage.CHAT_MESSAGE, user.getId(),chatId);
            messageService.add(newMessage);
        }
    }

    private static void openGroup() {
        System.out.println("Create group:");
        viewContact();
     Integer member = Scan.scanInt(" Group members");
     User user = UserServiceImpl.getUser(member);
     String name = Scan.scanStr("Enter Group Name");
        System.out.println("Created Group!!!");
        String text = Scan.scanStr("Enter Text");
        String groupId = UUID.randomUUID().toString();
        Message message = new Message(text,LogIn.getIdLogIn(),TypeMessage.GROUP_MESSAGE, user.getId(), groupId);
        messageService.add(message);
        groupService.add(new Group(name,message));
    }
    private static void showGroupMembers(List<Group> myGroups) {

        System.out.println("Groups: ");
        int i = 1;
        for (Group group : groupService.get()) {
            for (User user : userService.get()) {
                if (Objects.equals(group.getUserId(), user.getId())){
                    System.out.println(i + ". " + user.getName());
                    i++;
                }
            }
        }
        Integer index = Scan.scanInt("Choose Group: ");

    }


    private static void openChat() {
        viewContact();
        Integer choose = Scan.scanInt("Choose");
        User user = UserServiceImpl.getUser(choose);
        for (Chat chat : chatService.get()) {
            if (chat!=null && (Objects.equals(user.getId(),chat.getUserId2())||Objects.equals(user.getId(),chat.getUserId1()))){
                System.out.println("There is already a chat with this user!!!");
                return;
            }
        }
        String chatId = UUID.randomUUID().toString();
        String text = Scan.scanStr("Enter Text");
        Message message = new Message(text,LogIn.getIdLogIn(),TypeMessage.CHAT_MESSAGE,user.getId(), chatId);
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
        MenuProfile.profile();
    }
}