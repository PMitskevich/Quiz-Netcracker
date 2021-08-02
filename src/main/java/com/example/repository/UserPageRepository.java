package com.example.repository;

import com.example.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserPageRepository extends PagingAndSortingRepository<User, UUID> {
}