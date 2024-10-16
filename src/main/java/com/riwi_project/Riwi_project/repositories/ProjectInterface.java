package com.riwi_project.Riwi_project.repositories;

import com.riwi_project.Riwi_project.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectInterface extends JpaRepository<ProjectEntity, Long> {
}
