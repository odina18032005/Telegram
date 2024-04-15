package backend.model;

import backend.enums.TypeMessage;
import backend.service.UserService;
import backend.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private String id;
    private String userId1;
    private String userId2;

    private List<User> userListInGroup;

    private TypeMessage type;

    public Group(String name, Message message, String userId2) {
        this.name = name;
        this.id = message.getChatId();
        this.userId1 = message.getUserId();
        this.userId2 = userId2;
        this.type = message.getType();
        this.userListInGroup = new ArrayList<>();
        User user1 = UserServiceImpl.getUser(message.getUserId());
        User user2 = UserServiceImpl.getUser(userId2);
        userListInGroup.add(user1);
        userListInGroup.add(user2);
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeMessage getType() {
        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }
    public List<User> getUserListInGroup() {
        return userListInGroup;
    }

    public void setUserListInGroup(List<User> userListInGroup) {
        this.userListInGroup = userListInGroup;
    }
}
