package com.ws.dao;

import java.util.List;

import com.ws.model.Pagination;
import com.ws.model.User;

/**
 * The interface of user dao.
 * Gets user information by userName from database.
 * Update user information.
 * */
public interface UserDao {
    public User getUserByName(String userName);
    public void updateUser(User user);
    public void createUser(User user);
    public List<User> queryAllUser(Pagination pagination);
    public Integer getUserCount();
    public void deleteUser(Integer userId);
}
