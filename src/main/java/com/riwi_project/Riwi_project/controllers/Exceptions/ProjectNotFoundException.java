package com.riwi_project.Riwi_project.controllers.Exceptions;

public class ProjectNotFoundException extends RuntimeException{

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
