package com.riwi_project.Riwi_project.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
public class TaskEntity {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne
       @JoinColumn(name = "id_task")
    private TaskEntity taskEntity;
}
