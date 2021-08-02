package com.example.service.interfaces;

import com.example.model.User;
import com.example.security.LoginModel;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAllUser();

    User saveUser(LoginModel loginModel);

    User updateUser(UUID userId, User userRequest);

    void deleteUser(UUID userId);

    User getUserById(UUID userId);

    User findUserByUsername(String userName);

    boolean activateUser(String code);

    User blockUser(UUID userId);
}
