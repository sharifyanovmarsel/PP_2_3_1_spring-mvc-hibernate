package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;

import java.util.List;

@Component
public interface UserDAO {
    List<User> getUsersList();
    User show(int id);
    void update(int id, User updatedUser);
    void save(User user);
    void delete(int id);
}
