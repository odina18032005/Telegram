package backend.service;

import backend.dto.LoginDTO;
import backend.model.User;

public interface UserService extends BaseService<User> {
    boolean login(LoginDTO login);
}
