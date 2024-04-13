package backend.model;

import backend.enums.TypeMessage;

import java.util.List;

public class Group {
    private String name;
    private String id;
    private String userId;
    private TypeMessage type;

    public Group(String name, Message message) {
        this.name = name;
        this.id = message.getChatId();
        this.userId = message.getUserId();
        this.type = message.getType();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
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
}
