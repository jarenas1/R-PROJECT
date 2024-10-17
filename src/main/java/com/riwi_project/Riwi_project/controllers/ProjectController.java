package com.riwi_project.Riwi_project.controllers;

import com.riwi_project.Riwi_project.entities.ProjectEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;
import com.riwi_project.Riwi_project.entities.dto.response.ProjectDto;
import com.riwi_project.Riwi_project.entities.dto.response.UserDto;
import com.riwi_project.Riwi_project.services.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(originPatterns = "*") //TODAS LAS RUTAS DE FRONT TIENEN ACCESO
@Tag(
        name = "Perritos guaugau",
        description = "probando el swagger de riwi-project"

)
@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping
    public List<ProjectDto> findAll (){
        //Get all projects
        List<ProjectEntity>projectEntities=projectService.findAll();

        //List to save the dtos
        List<ProjectDto> projectsDtos = new ArrayList<>();

        //changing the entitiesa
        for(ProjectEntity project:projectEntities){
            ProjectDto projectDto = new ProjectDto();

            projectDto.setName(project.getName());
            for(UserEntity userEntity:project.getUsers()){
                UserDto userDto = new UserDto();
                userDto.setUsername(userEntity.getUsername());
                projectDto.getUsers().add(userDto);

                projectsDtos.add(projectDto);
            }
        }
        return projectsDtos;
    }

    @PostMapping
    private ProjectEntity create(@RequestBody ProjectEntity projectEntity){
        return projectService.save(projectEntity);
    }

    @PostMapping("/add")
    private void addNewUser(@RequestBody List<UserEntity> users, Long id){
        //find the project
        Optional<ProjectEntity> optionalProject = projectService.findById(id);

        //ADD THE NEW USERS AND SAVE
        optionalProject.ifPresent((project)->{
            List<UserEntity> usersAsociated = project.getUsers();

            usersAsociated.addAll(users);

            project.setUsers(usersAsociated);
            projectService.save(project);
        });
    }

    @DeleteMapping("/{id}")
    public ProjectEntity delete(@PathVariable Long id){
        return projectService.delete(id);
    }

    @GetMapping("/user")
    public List<ProjectDto> userProjects(@RequestBody UserEntity userEntity){
        List<ProjectEntity> projectEntities = projectService.findByUser(userEntity);

        List<ProjectDto> projectDtos = new ArrayList<>();

        for (ProjectEntity project:projectEntities){
            ProjectDto projectDto = new ProjectDto();
            projectDto.setName(project.getName());

            for (UserEntity user: project.getUsers()){
                UserDto userDto = new UserDto();
                userDto.setUsername(user.getUsername());
                projectDto.getUsers().add(userDto);
            }
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    //VALIDACION
    private ResponseEntity<?> validation(BindingResult result) {
        //CREACION DEL ERROR
        Map<String, String> errors = new HashMap<>();
        //Iteramos errores para añadirlos al map
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), "El campo "+fieldError.getField()+" "+ fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
