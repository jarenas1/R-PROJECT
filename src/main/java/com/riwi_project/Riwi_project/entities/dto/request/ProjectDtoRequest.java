package com.riwi_project.Riwi_project.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.riwi_project.Riwi_project.entities.TaskEntity;
import com.riwi_project.Riwi_project.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDtoRequest {

    private String name;

    private Date deadLine;

    private List<TaskEntity> tasks = new ArrayList<>();

    private List<UserEntity> users = new ArrayList<>();

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
