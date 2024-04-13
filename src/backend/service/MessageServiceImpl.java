package backend.service;

import backend.model.Message;

import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    public void update(Message newM) {

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
