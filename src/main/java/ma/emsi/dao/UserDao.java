package ma.emsi.dao;

import ma.emsi.entities.User;

public interface UserDao {
    User getUser(String username);
}
