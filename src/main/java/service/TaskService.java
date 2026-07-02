package service;

import application.Task;
import application.TaskStatus;
import org.springframework.stereotype.Repository;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static application.TaskStatus.PENDING;

public class TaskService {

    private TaskRepository tasks = new TaskRepository();

    public Task createTask(String title, TaskStatus status){
        Date now = new Date();

        if (status == null) {
            status = PENDING;
        }

        long id = tasks.tasks.size() + 1;
//        String id = UUID.randomUUID().toString();

        Task task = new Task(id,title,status,now,now);

        tasks.addTask(task);

        return task;
    }

    public Task getTaskByID(Long id){
        return tasks.getTask(id);
    }

    public TaskRepository getAllTasks(){
        return tasks;
    }

    public void deleteTask(Long id){
        if(tasks.getTask(id) != null){
            tasks.removeTask(id);
        }
    }
}
