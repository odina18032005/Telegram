package backend.service;

import backend.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageServiceImpl implements MessageService{
    static MessageService messageService;
    List<Message> messageList;

    private MessageServiceImpl() {
        this.messageList = new ArrayList<>();
    }

    @Override
    public boolean create(Message message) {
        return false;
    }

    @Override
    public boolean add(Message message) {
        return this.messageList.add(message);
    }

    @Override
    public void delete(String id) {
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i)!=null && messageList.get(i).getId().equals(id)){
                messageList.set(i,null);
            }
        }
    }

    @Override
    public void update(Message newM, int index) {

    }


    @Override
    public List<Message> get() {
        return messageList;
    }
    public static MessageService getInstance(){
        if (messageService==null){
            messageService = new MessageServiceImpl();
        }
        return messageService;
    }
}
