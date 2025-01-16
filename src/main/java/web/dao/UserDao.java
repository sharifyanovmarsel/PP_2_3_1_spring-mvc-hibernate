package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    void update(int id, User updatedUser);
    void save(User user);
    void delete(int id);
}
