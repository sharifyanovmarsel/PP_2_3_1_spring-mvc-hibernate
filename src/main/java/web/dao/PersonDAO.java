package web.dao;

import web.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<User> people;

    {
        people = new ArrayList<>();

        people.add(new User(++PEOPLE_COUNT, "Tom", 24, "tom@mail.ru"));
        people.add(new User(++PEOPLE_COUNT, "Bob", 52, "bob@mail.ru"));
        people.add(new User(++PEOPLE_COUNT, "Mike", 18, "mike@yahoo.com"));
        people.add(new User(++PEOPLE_COUNT, "Katy", 34, "katy@gmail.com"));
    }

    public List<User> index() {
        return people;
    }

    public User show(int id) {
        return people.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++PEOPLE_COUNT);
        people.add(user);
    }

    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);

        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setAge(updatedUser.getAge());

    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}