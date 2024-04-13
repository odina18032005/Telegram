package backend.service;

import backend.enums.TypeMessage;
import backend.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupServiceImpl implements GroupService {
    static GroupService groupService;
    List<Group> groupList;

    private GroupServiceImpl() {
        this.groupList = new ArrayList<>();
    }

    @Override
    public boolean create(Group group) {
        if (group!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean add(Group group) {
        return this.groupList.add(group);
    }

    @Override
    public void delete(String id) {
        for (Group group : groupList) {
            if (Objects.equals(group.getId(),id)){
                groupList.remove(group);
            }
        }
    }

    @Override
    public void update(Group newM) {

    }

    @Override
    public List<Group> get() {
        return groupList;
    }

    @Override
    public void getList(TypeMessage type) {
        int i = 1;
        for (Group group : groupList) {
            System.out.println(i + ". " + group);
            i++;
        }
    }
    public static GroupService getInstance(){
        if (groupService==null){
            groupService = new GroupServiceImpl();
        }
        return groupService;
    }
}
