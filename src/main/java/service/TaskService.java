package service;

import module.Task;
import module.TaskStatus;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.Date;

import static module.TaskStatus.PENDING;

@Service //componenta care tine logica de business
public class TaskService {

    private final TaskRepository repository = new TaskRepository();

    public Task createTask(String title, TaskStatus status) {
        Date now = new Date();

        if (status == null) {
            status = PENDING;
        }

        long id = repository.getSize();
//        String id = UUID.randomUUID().toString();

        Task task = new Task(id, title, status, now, now);

        repository.addTask(task);

        return task;
    }

    public Task getTaskByID(Long id) {
        return repository.getTask(id);
    }

    public ArrayList<Task> getAllTasks() {
        return repository.getAllTasks();
    }

    public void updateTask(Long id, String title, TaskStatus taskStatus){
        repository.updateTask(id,title,taskStatus);
    }

    public void deleteTask(Long id) {
        repository.removeTask(id);
    }
}
