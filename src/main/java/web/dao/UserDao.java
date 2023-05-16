package web.dao;

import web.models.User;
import java.util.List;

public interface UserDao {
    void createUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    User getUser(long id);
    List<User> getAllUsers();
}
