package com.riwi_project.Riwi_project.entities.dto.response;

import java.util.List;

public class ProjectDto {
    private String name;

    private List<UserDto> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
