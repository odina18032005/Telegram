package ui;

import backend.enums.TypeMessage;
import backend.forScanner.Scan;
import backend.model.Chat;
import backend.model.Message;
import backend.model.User;
import backend.registratdiya.LogIn;
import backend.service.*;

import java.util.Objects;
import java.util.UUID;

public class MenuChats {
    private static UserService userService = UserServiceImpl.getInstance();
    private static ChatService chatService = ChatServiceImpl.getInstance();
    private static MessageService messageService = MessageServiceImpl.getInstance();

    public static void chat() {
        System.out.println("Your Chat");
        while (true){
            menu();
            Integer choose = Scan.scanInt("Choose");
            switch (choose){
                case 1-> openChat();
                case 2-> chats();
                case 3-> deleteChat();
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

    private static void menu() {
        System.out.println("=========================================");
        System.out.println("""
                1. Open chat
                2. Chats
                3. Delete chat
                0. Exit
                """);
        System.out.println("=========================================");
    }

    private static void openChat() {
        FrontEnd.viewContact();
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
        Message message = new Message(text, LogIn.getIdLogIn(), TypeMessage.CHAT_MESSAGE,user.getId(), chatId);
        messageService.add(message);
        assert user != null;
        chatService.add(new Chat(message, user));
    }
    private static void chats() {
        FrontEnd.viewContact();
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
                            return;
                        }
                    }
                }
            }
            String text = Scan.scanStr("Enter Text");
            Message newMessage = new Message(text,LogIn.getIdLogIn(),TypeMessage.CHAT_MESSAGE, user.getId(),chatId);
            messageService.add(newMessage);
        }
    }
    private static void deleteChat() {
        FrontEnd.viewContact();
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
}
