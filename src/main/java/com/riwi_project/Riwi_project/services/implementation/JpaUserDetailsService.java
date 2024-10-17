package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.entities.UserEntity;
import com.riwi_project.Riwi_project.repositories.UserRepository;
import com.riwi_project.Riwi_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JpaUserDetailsService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findAll() {
        return List.of();
    }
}
