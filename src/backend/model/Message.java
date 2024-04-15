package backend.model;

import backend.enums.TypeMessage;
import backend.service.UserService;
import backend.service.UserServiceImpl;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Message {
    private String user;
    private String text;
    private String chatId;
    private String userId;
    private String id;
    private TypeMessage type;
    private LocalTime time;

    public Message(String text, String userId, TypeMessage type, String user, String chatId) {
        this.text = text;
        this.chatId = chatId;
        this.userId = userId;
        this.type = type;
        this.id = UUID.randomUUID().toString();
        this.time = LocalTime.now();
        this.user = user;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChatId() {
        return chatId;
    }

    public String getUserId() {
        return userId;
    }

    public TypeMessage getType() {
        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        UserService userService = UserServiceImpl.getInstance();
        for (User user1 : userService.get()) {
            if (Objects.equals(user1.getId(),userId)){
                return  "                          " + time + "\n" + user1.getName() + ": " + text;
            }
        }
        return null;
    }
}

