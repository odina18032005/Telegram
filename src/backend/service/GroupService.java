package backend.service;

import backend.enums.TypeMessage;
import backend.model.Group;

import java.util.List;

public interface GroupService extends BaseService<Group> {
    void update(Group newM);

    void getList(TypeMessage type);
}
