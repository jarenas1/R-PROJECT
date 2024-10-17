package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.controllers.Exceptions.RoleNotFoundException;
import com.riwi_project.Riwi_project.entities.RoleEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;
import com.riwi_project.Riwi_project.repositories.RoleRepository;
import com.riwi_project.Riwi_project.repositories.UserRepository;
import com.riwi_project.Riwi_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserImp implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {

        //get the default role using findByName
        Optional<RoleEntity> role = roleRepository.findByName("ROLE_USER");

        List<RoleEntity> roles = new ArrayList<>();

        //Check if role was founded
        role.ifPresentOrElse(roleEntity ->{
            roles.add(roleEntity);

            if (userEntity.isAdmin()){
                Optional<RoleEntity> roleAdmin = roleRepository.findByName("ROLE_ADMIN");

                roleAdmin.ifPresent(roleA-> roles.add(roleA));
            }
            return;
        },()->{
            throw new RoleNotFoundException("No se pudo encontrar el rol, hay problemas en la db");
        } );

        //Ser the roles to the user
        userEntity.setRoles(roles);

        //encode the password
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
