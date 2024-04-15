package backend.service;

import backend.enums.TypeMessage;
import backend.model.Group;
import backend.model.User;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    static GroupService groupService;
   static List<Group> groupList;

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
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i)!=null && groupList.get(i).getId().equals(id)){
                groupList.set(i,null);
            }
        }
    }

    @Override
    public void update(Group newM, int index) {

    }

    @Override
    public List<Group> get() {
        return groupList;
    }

    @Override
    public void update(Group newM) {

    }

    @Override
    public void getList(TypeMessage type) {
        int i = 1;
        for (Group group : groupList) {
            System.out.println(i + ". " + group);
            i++;
        }
    }

    @Override
    public Group getGroup(Integer choose) {
        int i = 0;
        for (Group group : groupList) {
            if (i==choose){
                return group;
            }
            i++;
        }
        return null;
    }

    public static GroupService getInstance(){
        if (groupService==null){
            groupService = new GroupServiceImpl();
        }
        return groupService;
    }
}
