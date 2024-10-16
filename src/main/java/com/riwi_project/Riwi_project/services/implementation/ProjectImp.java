package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.entities.ProjectEntity;
import com.riwi_project.Riwi_project.repositories.ProjectRepository;
import com.riwi_project.Riwi_project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProjectImp implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectEntity save(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    @Override
    public Optional<ProjectEntity> findById(Long id) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(id);
        return optionalProject;
    }

    @Override
    public List<ProjectEntity> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectEntity delete(Long id) {
        Optional<ProjectEntity> optionalProject = projectRepository.findById(id);

        optionalProject.ifPresentOrElse((projectEntity -> {
            projectRepository.delete(optionalProject.get());
        }()->{
            throw new Projec
        }));
        return null;
    }
}
