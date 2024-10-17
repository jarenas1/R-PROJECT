package com.riwi_project.Riwi_project.services;

import com.riwi_project.Riwi_project.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save (UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

    List<UserEntity> findAll();


}
