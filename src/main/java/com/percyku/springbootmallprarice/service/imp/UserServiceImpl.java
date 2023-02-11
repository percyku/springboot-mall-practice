package com.percyku.springbootmallprarice.service.imp;


import com.percyku.springbootmallprarice.dao.UserDao;
import com.percyku.springbootmallprarice.dto.UserRegisterRequest;
import com.percyku.springbootmallprarice.model.User;
import com.percyku.springbootmallprarice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //check register email
        User user =userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null){
            log.warn("This email {} had been registered",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //create user account
        return userDao.createUser(userRegisterRequest);
    }

}
