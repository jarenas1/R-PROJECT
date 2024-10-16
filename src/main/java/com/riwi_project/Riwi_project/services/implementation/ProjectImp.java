package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.entities.ProjectEntity;
import com.riwi_project.Riwi_project.services.ProjectService;

import java.util.List;
import java.util.Optional;

public class ProjectImp implements ProjectService {
    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {
        return null;
    }

    @Override
    public Optional<ProjectEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ProjectEntity> findAll() {
        return List.of();
    }

    @Override
    public ProjectEntity delete(Long id) {
        return null;
    }
}
