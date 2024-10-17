package com.riwi_project.Riwi_project.services;

import com.riwi_project.Riwi_project.entities.ProjectEntity;
import com.riwi_project.Riwi_project.entities.TaskEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    ProjectEntity save (ProjectEntity projectEntity);

    Optional<ProjectEntity> findById(Long id);

    List<ProjectEntity> findAll();

    ProjectEntity delete(Long id);

    List<ProjectEntity> findByUser(UserEntity userEntity);
}
