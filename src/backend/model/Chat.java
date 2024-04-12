package backend.model;

import backend.enums.TypeMessage;

import java.time.LocalTime;

public class Chat {
    private String id;
    private String userId1;
    private String userId2;
    private TypeMessage type;

    public Chat(Message id, Message userId1, String userId2, TypeMessage type) {
        this.id = id.getChatId();
        this.userId1 = userId1.getUserId();
        this.userId2 = userId2;
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
