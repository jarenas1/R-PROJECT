package com.riwi_project.Riwi_project.services;

import com.riwi_project.Riwi_project.entities.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskEntity save (TaskEntity taskEntity);

    Optional<TaskEntity> findById(Long id);

    List<TaskEntity> findAll();

    TaskEntity delete(Long id);
}
