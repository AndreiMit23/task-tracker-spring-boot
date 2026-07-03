package com.example.task_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Am facut asta ca eu am scris pachetele inafara pachetului com.example... :))))))))))))))))))
//Am incercat varianta cu mutarea pachetelor dar nu mergea... si am ales asta
@SpringBootApplication(scanBasePackages = {
        "controller",
        "service",
        "repository",
        "module"
})
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

}
