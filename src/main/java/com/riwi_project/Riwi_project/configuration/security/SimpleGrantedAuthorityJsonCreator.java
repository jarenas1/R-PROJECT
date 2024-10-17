package com.riwi_project.Riwi_project.configuration.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {
    @JsonCreator                              //DEL JSON authority PUEBLE LOS role
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){}
}
