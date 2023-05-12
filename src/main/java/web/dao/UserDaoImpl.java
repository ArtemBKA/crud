package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import javax.persistence.*;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory emf;

    @Autowired
    public UserDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        EntityManager manager = emf.createEntityManager();
        return manager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        EntityManager manager = emf.createEntityManager();
        return manager.find(User.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        EntityManager manager = emf.createEntityManager();
        User user = null;
        TypedQuery<User> query = manager.createQuery("select u from User u where u.email = ?1", User.class);
        query.setParameter(1, email);
        try {
            user = query.getSingleResult();
        } catch (NoResultException ignore) {
            /* NOP */
        }
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        EntityManager manager = emf.createEntityManager();
        User user = getUserById(id);
        manager.getTransaction().begin();
        manager.remove(manager.contains(user) ? user : manager.merge(user));
        manager.getTransaction().commit();
    }
}