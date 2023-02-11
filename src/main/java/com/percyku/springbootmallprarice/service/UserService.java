package com.percyku.springbootmallprarice.service;


import com.percyku.springbootmallprarice.dto.UserRegisterRequest;
import com.percyku.springbootmallprarice.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
