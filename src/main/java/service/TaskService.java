package service;

import module.Task;
import module.TaskStatus;
import org.springframework.stereotype.Service;
import repository.ITaskRepository;

import java.util.Date;
import java.util.List;

import static module.TaskStatus.PENDING;

@Service //componenta care tine logica de business
public class TaskService {

    private final ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
//        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, TaskStatus status) {
        Date now = new Date();

        if (status == null) {
            status = PENDING;
        }

//        long id = repository.getSize();
//        String id = UUID.randomUUID().toString();

        iTaskRepository.count();

        Task task = new Task(title, status, now, now);

        iTaskRepository.save(task);

        return task;
    }

    public Task getTaskByID(Long id) {
        return iTaskRepository.findById(id).get();
    }

    public List<Task> getAllTasks() {
        return iTaskRepository.findAll();
    }

    public void updateTask(Long id, String title, TaskStatus taskStatus) {
        Date now = new Date();
        Task task = new Task(id, title, taskStatus, now, now);
        iTaskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        iTaskRepository.deleteById(id);
    }

    public void deleteTask(Task task) {
        if (task.getId() == null || task.getTitle() == null || task.getStatus() == null)
            throw new IllegalArgumentException("All fields are required: id, title,status");
        else
            iTaskRepository.delete(task);
    }

    public List<Task> findByTitle(String title){
        return iTaskRepository.findByTitle(title);
    }

    public List<Task> findByStatus(TaskStatus status){
        if(status == null)
            throw new IllegalArgumentException("Status field is required: status: ");
        return iTaskRepository.findByStatus(status);
    }
}
