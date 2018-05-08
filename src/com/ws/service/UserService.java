package com.ws.service;

import java.util.List;

import com.ws.exception.ParameterException;
import com.ws.exception.ServiceException;
import com.ws.model.Pagination;
import com.ws.model.User;

/**
 * The interface of UserService.
 * */
public interface UserService {

    public User login(String userName, String password) throws ParameterException,ServiceException;

    public void updateUser(User user);

    public User getUser(String userName);
    
    public void createUser(User user);
    
    public List<User> getAllUser(Pagination pagination);
    
    public void deleteUser(Integer userId);
}
