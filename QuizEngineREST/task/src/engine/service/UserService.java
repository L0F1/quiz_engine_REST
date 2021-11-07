package engine.service;

import engine.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    boolean saveUser(User user);
    User getCurrentAuthUser();

}
