package com.example;

import com.example.jpa.Task;
import com.example.jpa.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        log.info("Welcome to the new millenium !!");

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo( TaskRepository repository) {
        return (args) -> {
            // save a couple of customers
            repository.save(new Task("Jack", "Bauer"));
            repository.save(new Task("Chloe", "O'Brian"));
            repository.save(new Task("Kim", "Bauer"));
            repository.save(new Task("David", "Palmer"));
            repository.save(new Task("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Task customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByDescription("Jack").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info(" - end -");
        };
    }

}