package web.dao;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import web.config.AppConfig;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsersList() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public User show(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Transactional
    public void update(int id, User updatedUser) {
        User user = show(id);
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        user.setEmail(updatedUser.getEmail());
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(show(id));
    }

}
