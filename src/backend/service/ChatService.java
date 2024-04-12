package backend.service;

import backend.enums.TypeMessage;
import backend.model.Chat;
import backend.model.User;

import java.util.ArrayList;
import java.util.List;

public interface ChatService extends BaseService<Chat>{
    void getList(TypeMessage type);
}
