package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private final Map<Integer, User> model;

    private static final Model instance = new Model();

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("qqq", "ww", 111111.0));
        model.put(2, new User("aaa", "ss", 113333.0));
        model.put(3, new User("zzz", "xx", 115555.0));
        model.put(4, new User("qaz", "wsx", 223415.0));
    }

    public void add(int id, User user) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
