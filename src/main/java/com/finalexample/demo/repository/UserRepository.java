package com.finalexample.demo.repository;

import com.finalexample.demo.model.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}

