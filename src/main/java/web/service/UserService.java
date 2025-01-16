package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void update(int id, User updatedUser);
    void save(User user);
    void delete(int id);
}
