package web.dao;

import web.models.User;
import java.util.List;

public interface UserDao {
    List<User> getUsers();
    User getUserById(int id);
    User getUserByEmail(String email);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
