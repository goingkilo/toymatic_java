package com.example.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByDescription(String description);
    List<Task> findByOwner(String owner);

}
