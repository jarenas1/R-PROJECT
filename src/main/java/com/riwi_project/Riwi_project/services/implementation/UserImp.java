package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.entities.RoleEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;
import com.riwi_project.Riwi_project.repositories.RoleRepository;
import com.riwi_project.Riwi_project.repositories.UserRepository;
import com.riwi_project.Riwi_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserImp implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {

        //get the default role using findByName
        Optional<RoleEntity> role = roleRepository.findByName("ROLE_USER");

        List<RoleEntity> roles = new ArrayList<>();

        //Check if role was founded
        role.ifPresentOrElse(roleEntity ->{
            roles.add(roleEntity);
            return;
        },()->{
            throw
        } );
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
