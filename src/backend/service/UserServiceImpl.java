package backend.service;

import backend.dto.LoginDTO;
import backend.model.Group;
import backend.model.User;
import backend.registratdiya.LogIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService{
    static UserService userService;
    List<User> userList;

    public UserServiceImpl() {
        this.userList = new ArrayList<>();
        this.userList.add(new User("Zahro","zahi",977810728,"1441"));
        this.userList.add(new User("Odina","dindin",900970680,"1234"));
        this.userList.add(new User("Zilola","zilol",909099909,"1221"));
        this.userList.add(new User("Omina","odin",900800708,"2552"));
    }

    @Override
    public boolean login(LoginDTO login) {
        for (User user : userList) {
            if (Objects.equals(user.getPhoneNumber(), login.phoneNumber())
                    && Objects.equals(user.getPassword(), login.password())
            ) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean add(User user) {
        if (user!=null){
            userList.add(user);
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {
        for (User user : userList) {
            if (Objects.equals(user.getId(),id)){
                userList.remove(user);
            }
        }
    }

    @Override
    public void update(User newM, int index) {
        userList.set(index,newM);
    }

    @Override
    public List<User> get() {
        return userList;
    }
    public static User getUser(Integer choose){
        int i = 1;
        for (User user : userService.get()) {
            if(!Objects.equals(user.getId(), LogIn.getIdLogIn())){
                if (choose==i){
                    return user;
                }
                i++;
            }
        }
        return null;
    }

    public static UserService getInstance(){
        if (userService==null){
            userService = new UserServiceImpl();
        }
        return userService;
    }
}
