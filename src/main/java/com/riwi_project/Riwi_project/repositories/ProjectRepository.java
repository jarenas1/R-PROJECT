package com.riwi_project.Riwi_project.repositories;

import com.riwi_project.Riwi_project.entities.ProjectEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findByUser(UserEntity userEntity);
}
