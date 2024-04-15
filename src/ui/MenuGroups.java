package ui;

import backend.enums.TypeMessage;
import backend.forScanner.Scan;
import backend.model.Group;
import backend.model.Message;
import backend.model.User;
import backend.registratdiya.LogIn;
import backend.service.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class MenuGroups {
    private static UserService userService = UserServiceImpl.getInstance();
    private static GroupService groupService = GroupServiceImpl.getInstance();
    private static MessageService messageService = MessageServiceImpl.getInstance();

    public static void group() {
        System.out.println("Your Groups");
        while (true){
            menu();
            Integer choose = Scan.scanInt("Choose");
            switch (choose){
                case 1-> openGroup();
                case 2-> groups();
                case 3-> addMember();
                case 4-> showGroupMembers();
                case 5-> deleteGroup();
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
                1. Open group
                2. Groups
                3. Add member
                4. Show member in group
                5. Delete group
                0. Exit
                """);
        System.out.println("=========================================");
    }

    private static void openGroup() {
        System.out.println("Create group");
        FrontEnd.viewContact();
        Integer member = Scan.scanInt(" Group members");
        User user = UserServiceImpl.getUser(member);
        String name = Scan.scanStr("Enter Group Name");
        System.out.println("Created Group!!!");
        String text = Scan.scanStr("Enter Text");
        String groupId = UUID.randomUUID().toString();
        Message message = new Message(text,LogIn.getIdLogIn(),TypeMessage.GROUP_MESSAGE, user.getId(), groupId);
        messageService.add(message);
        groupService.add(new Group(name,message,user.getId()));
    }
    private static void addMember() {
        boolean b = viewsGroups();
        if (b){
            Integer choose = Scan.scanInt("Choose")-1;
            Group group = groupService.getGroup(choose);
            if (group!=null){
                FrontEnd.viewContact();
                Integer userChoose = Scan.scanInt("Choose");
                User user = UserServiceImpl.getUser(userChoose);
                for (User user1 : group.getUserListInGroup()) {
                    if (Objects.equals(user1.getId(),user.getId())){
                        System.out.println("There is already a chat with this user!!!");
                        return;
                    }else {
                        group.getUserListInGroup().add(user);
                    }
                }
            }
        }else {
            return;
        }
    }
    private static void showGroupMembers() {
        boolean b = viewsGroups();
        if (b){
            Integer choose = Scan.scanInt("Choose")-1;
            Group group = groupService.getGroup(choose);
            int i = 1;
            if (group!=null){
                for (User user : group.getUserListInGroup()) {
                    System.out.println(i + ". " + user.getName());
                    i++;
                }
            }
        }else {
            return;
        }
    }
    private static void groups() {
        boolean b = viewsGroups();
        if (b){
            Integer choose = Scan.scanInt("Choose") - 1;
            Group group = groupService.getGroup(choose);
            String groupId = "";
            String userId = "";
            String name = "";
            if (group != null) {
                System.out.println("                          " + group.getName());
                for (Message message : messageService.get()) {
                    if (Objects.equals(group.getId(), message.getChatId())) {
                        System.out.println(message);
                        groupId = group.getId();
                        userId = group.getUserId();
                        name = group.getName();
                    }
                }
            }
            String text = Scan.scanStr("Enter Text");
            Message newMessage = new Message(text, LogIn.getIdLogIn(), TypeMessage.GROUP_MESSAGE, userId, groupId);
            messageService.add(newMessage);
        }else {
            return;
        }
    }
    private static void deleteGroup() {
        boolean b = viewsGroups();
        if (b){
            Integer choose = Scan.scanInt(" Choose")-1;
            Group group = GroupServiceImpl.getInstance().getGroup(choose);
            for (User user : userService.get()) {
                if (group!= null && Objects.equals(user.getId(),LogIn.getIdLogIn())){
                    group.getUserListInGroup().remove(user);
                }
            }
        }else {
            return;
        }
    }
    private static boolean viewsGroups(){
        boolean tek  = false;
        int i = 1;
        for (Group group : groupService.get()) {
            for (User user : group.getUserListInGroup()) {
                if (Objects.equals(user.getId(),LogIn.getIdLogIn())){
                    System.out.println(i + ". " + group.getName());
                    i++;
                    tek = true;
                }
            }
        }
        if (tek){
            return true;
        }else {
            System.out.println("You don't have a group chat!!!");
            return false;
        }
    }
}
