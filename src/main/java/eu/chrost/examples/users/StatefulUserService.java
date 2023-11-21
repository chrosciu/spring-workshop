package eu.chrost.examples.users;

import java.util.ArrayList;
import java.util.List;

public class StatefulUserService {
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    void cleanUp() {
        users.clear();
    }
}
