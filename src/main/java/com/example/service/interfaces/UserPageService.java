package com.example.service.interfaces;

import com.example.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserPageService {
    Page<User> findAll(PageRequest pageRequest);
}
