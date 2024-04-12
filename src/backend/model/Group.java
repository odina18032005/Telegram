package backend.model;

import backend.enums.TypeMessage;

public class Group {
    private String name;
    private String id;
    private String userId;
    private TypeMessage type;

    public Group(String name, Message id, Message userId, TypeMessage type) {
        this.name = name;
        this.id = id.getChatId();
        this.userId = userId.getUserId();
        this.type = type;
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
