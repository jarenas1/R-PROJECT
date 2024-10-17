package com.riwi_project.Riwi_project.services.implementation;

import com.riwi_project.Riwi_project.controllers.Exceptions.ProjectNotFoundException;
import com.riwi_project.Riwi_project.entities.ProjectEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;
import com.riwi_project.Riwi_project.repositories.ProjectRepository;
import com.riwi_project.Riwi_project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

        optionalProject.ifPresentOrElse(projectEntity -> {
            projectRepository.delete(optionalProject.get());
            return;
        },()->{
            throw new ProjectNotFoundException("No se pudo encontrar el proyecto a eliminar");
        });
        return optionalProject.get();
    }

    @Override
    public List<ProjectEntity> findByUser(UserEntity userEntity) {
        return projectRepository.findByUser(userEntity);
    }
}
