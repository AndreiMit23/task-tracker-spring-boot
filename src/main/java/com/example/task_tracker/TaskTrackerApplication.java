package com.example.task_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//used for packageScan, because my packages are not in the same package as TaskTrackerApp
@SpringBootApplication(scanBasePackages = {
        "controller",
        "service",
        "repository",
        "exception",
        "mapper",
        "module"
})
@EnableJpaRepositories(basePackages = "repository")
@EntityScan(basePackages = "module")
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

}