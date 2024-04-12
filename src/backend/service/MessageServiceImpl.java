package backend.service;

import backend.model.Message;

import java.util.List;

public class MessageServiceImpl implements MessageService{
    static MessageService messageService;
    List<Message> messageList;

    @Override
    public boolean create(Message message) {
        return false;
    }

    @Override
    public boolean add(Message message) {
        if (message!=null){
            messageList.add(message);
            return true;
        }
        return false;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Message newM) {

    }

    @Override
    public List<Message> get() {
        return null;
    }
    public static MessageService getInstance(){
        if (messageService==null){
            messageService = new MessageServiceImpl();
        }
        return messageService;
    }
}
