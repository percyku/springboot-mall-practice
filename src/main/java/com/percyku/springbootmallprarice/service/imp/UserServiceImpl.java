package com.percyku.springbootmallprarice.service.imp;


import com.percyku.springbootmallprarice.dao.UserDao;
import com.percyku.springbootmallprarice.dto.UserRegisterRequest;
import com.percyku.springbootmallprarice.model.User;
import com.percyku.springbootmallprarice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {


        //create user account
        return userDao.createUser(userRegisterRequest);
    }

}
