package backend.model;

import backend.enums.TypeMessage;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Chat {
    private String id;
    private String userId1;
    private String userId2;
    private TypeMessage type;
    static List<Message> messageList;

    public Chat(Message id, Message userId1, User userId2, TypeMessage type) {
        this.id = id.getChatId();
        this.userId1 = userId1.getUserId();
        this.userId2 = userId2.getId();
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getUserId1() {
        return userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public TypeMessage getType() {

        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }

}
