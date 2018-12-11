package com.example.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {


    @Autowired
    TaskRepository repository;

    @RequestMapping("/taskos")
    public List<Task> tasks() {

        repository.save(new Task("Jack1", "Bauer"));
        repository.save(new Task("Chloe1", "O'Brian"));
        repository.save(new Task("Kim1", "Bauer"));
        repository.save(new Task("David1", "Palmer"));
        repository.save(new Task("Michelle", "Dessler"));


        List<Task> l = repository.findByDescription("Jack1");
        return l;
    }

//    @Bean
//    public CommandLineRunner demo( TaskRepository repository) {
//        return (args) -> {
//            // save a couple of customers
//            repository.save(new Task("Jack", "Bauer"));
//            repository.save(new Task("Chloe", "O'Brian"));
//            repository.save(new Task("Kim", "Bauer"));
//            repository.save(new Task("David", "Palmer"));
//            repository.save(new Task("Michelle", "Dessler"));
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Task customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            repository.findById(1L)
//                    .ifPresent(customer -> {
//                        log.info("Customer found with findById(1L):");
//                        log.info("--------------------------------");
//                        log.info(customer.toString());
//                        log.info("");
//                    });
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByDescription("Jack").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            log.info(" - end -");
//        };
//    }

}