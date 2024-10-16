package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.controllers.Exceptions.TaskNotFoundException;
import com.riwi_project.Riwi_project.entities.TaskEntity;
import com.riwi_project.Riwi_project.repositories.TaskRepository;
import com.riwi_project.Riwi_project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TaskImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskEntity save(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    @Override
    public Optional<TaskEntity> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity delete(Long id) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);

        optionalTask.ifPresentOrElse(taskEntity -> {
            taskRepository.delete(taskEntity);
            return;
        },()->{
            throw new TaskNotFoundException("No se pudo eliminar la tarea con el id" + id);
        });
        return null;
    }
}
