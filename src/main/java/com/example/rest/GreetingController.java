package com.example.rest;

import com.example.redis.Student;
import com.example.redis.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {


    @Autowired
    StudentRepository studentRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {

        Student student = new Student("Eng2015001", name, Student.Gender.MALE, 1);

        studentRepository.save(student);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }


}