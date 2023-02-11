package com.percyku.springbootmallprarice.dao;


import com.percyku.springbootmallprarice.dto.UserRegisterRequest;
import com.percyku.springbootmallprarice.model.User;

public interface UserDao {

    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);

}
