package com.example.service.impl;

import com.example.model.Game;
import com.example.model.User;
import com.example.repository.GamePageRepository;
import com.example.repository.UserPageRepository;
import com.example.service.interfaces.UserPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserPageServiceImpl implements UserPageService {

    private final UserPageRepository userPageRepository;

    @Autowired
    public UserPageServiceImpl(UserPageRepository userPageRepository) {
        this.userPageRepository = userPageRepository;
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userPageRepository.findAll(pageRequest);
    }
}
