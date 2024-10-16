package com.riwi_project.Riwi_project.repositories;

import com.riwi_project.Riwi_project.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
