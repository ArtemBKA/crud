package web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import web.dao.UserDao;
import web.models.User;

@Component
public class UserValidator implements Validator {
    private final UserDao userDao;

    @Autowired
    public UserValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userDao.getUserByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
