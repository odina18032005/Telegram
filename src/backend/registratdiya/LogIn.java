package backend.registratdiya;

import backend.dto.LoginDTO;
import backend.forScanner.Scan;
import backend.model.User;
import backend.service.UserService;
import backend.service.UserServiceImpl;

import java.util.List;
import java.util.Objects;

public class LogIn {
    static UserService userService = UserServiceImpl.getInstance();
    static String idLogIn;
    public static boolean logIn(){
        System.out.println("Enter logIn info:");
        Integer phoneNumber = Scan.scanInt("PhoneNumber");
        String password = Scan.scanStr("Password");
        LoginDTO loginDTO = new LoginDTO(phoneNumber, password);
        boolean login = userService.login(loginDTO);
        for (User user : userService.get()) {
            if (Objects.equals(phoneNumber,user.getPhoneNumber())){
                idLogIn = user.getId();
            }
        }

        return login;
    }
    public static User signUp(){
        String name = Scan.scanStr("Enter Name");
        String username = Scan.scanStr("Enter Username");
        Integer phoneNumber = Scan.scanInt("Enter PhoneNumber");
        String password = Scan.scanStr("Enter Password");
        User user = new User(name, username, phoneNumber, password);
        boolean add = userService.add(user);
        if (add){
            idLogIn = user.getId();
            return user;
        }
        return null;
    }
    public static String getIdLogIn(){
        return idLogIn;
    }

    public static void setIdLogIn(String idLogIn) {
        LogIn.idLogIn = idLogIn;
    }

    public static Integer menuLogIn(){
        System.out.println("""
                       Menu:
                       1. LogIn
                       2. SignUp
                       0. Exit
                       """);
        return Scan.scanInt("Choose");

    }

}
