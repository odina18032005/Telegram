package backend.service;

import backend.enums.TypeMessage;
import backend.model.Chat;
import backend.model.Group;
import backend.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatServiceImpl implements ChatService{
    static ChatService chatService;
    List<Chat> chatList;
    @Override
    public boolean create(Chat chat) {
        if (chat!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Chat chat) {
        if (chat!=null){

            return  chatList.add(chat);
        }
        return false;
    }

    @Override
    public void delete(String id) {
        for (Chat chat : chatList) {
            if (Objects.equals(chat.getId(),id)){
                chatList.remove(chat);
            }
        }
    }

    @Override
    public void update(Chat newM) {

    }

    @Override
    public List<Chat> get() {
        return chatList;
    }

    @Override
    public List<Chat> getList(TypeMessage type) {
        if (chatList!= null) {
            return chatList;
        }
        return null;
    }
    public static ChatService getInstance(){
        if (chatService==null){
            chatService = new ChatServiceImpl();
        }
        return chatService;
    }
}
