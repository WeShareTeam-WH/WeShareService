package com.ws.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.ws.Constant;
import com.ws.dao.UserDao;
import com.ws.exception.ParameterException;
import com.ws.exception.ServiceException;
import com.ws.model.Pagination;
import com.ws.model.User;
import com.ws.service.UserService;
import com.ws.util.MD5Util;
import com.ws.util.StringUtil;
/**
 * The class is implementation class of userService.
 * and control the connection.
 * */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String userName, String password) throws ParameterException,ServiceException{
        ParameterException parameterException = new ParameterException();
        if (StringUtil.isEmpty(userName)) {
            parameterException.addValue(Constant.ERROR_NAME_MESSAGE, Constant.NAME_BLANK);
        }

        if (StringUtil.isEmpty(password)) {
            parameterException.addValue(Constant.ERROR_PASSWORD_MESSAGE, Constant.PASSWORD_BLANK);
        }

        if (parameterException.isErrorMessage()) {
            throw parameterException;
        }
        User user = userDao.getUserByName(userName);
        if (user == null) {
            logger.info(Constant.ERROR);
            throw new ServiceException(Constant.NAME_ERROR_CODE, Constant.ERROR);
        }
        String md5Password = MD5Util.getEncryptPassword(password);
        /*if (!md5Password.equals(user.getPassword())) {
            logger.debug(Constant.ERROR);
            throw new ServiceException(Constant.NAME_ERROR_CODE, Constant.ERROR);
        }*/
        return user;
    }

    @Override
    public void updateUser(User user) {
        /*if(StringUtil.isEmpty(user.getPassword())) {
            userDao.updateUser(user);
        }else {
            userDao.updateUser(user);
        }*/
    }

    @Override
    public User getUser(String userName) {
        User user = userDao.getUserByName(userName);
        return user;
    }

	@Override
	public void createUser(User user) {
		userDao.createUser(user);
	}

	@Override
	public List<User> getAllUser(Pagination pagination) {
		return userDao.queryAllUser(pagination);
	}

	@Override
	public void deleteUser(Integer userId) {
		userDao.deleteUser(userId);
	}
}
