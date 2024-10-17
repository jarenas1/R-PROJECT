package com.riwi_project.Riwi_project.controllers.Exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String message) {
        super(message);
    }
}