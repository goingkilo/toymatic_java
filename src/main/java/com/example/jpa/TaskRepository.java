package com.example.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TaskRepository extends CrudRepository<Task, Long> {

    @Transactional
    List<Task> findByDescription(String description);

    List<Task> findByOwner(String owner);

}
