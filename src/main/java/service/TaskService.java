package service;

import module.Task;
import module.TaskRequest;
import module.TaskStatus;
import org.springframework.stereotype.Service;
import repository.ITaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service //componenta care tine logica de business
public class TaskService {

    private final ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
//        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskRequest taskRequest) {

//        long id = repository.getSize();
//        String id = UUID.randomUUID().toString();
//         iTaskRepository.count();

        Task task = new Task(taskRequest.getTitle(),taskRequest.getDescription(), taskRequest.getPriority(),taskRequest.getDueDate());

        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        iTaskRepository.save(task);

        return task;
    }

    public Task getTaskByID(Long id) {
        return iTaskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id " + id + " not found"));
    }

    public List<Task> getAllTasks() {
        return iTaskRepository.findAll();
    }

    public void updateTask(Long id, TaskRequest taskRequest){

        Task task = iTaskRepository.findById(id).orElseThrow();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setTaskPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());

        task.setUpdatedAt(LocalDateTime.now());

        iTaskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        iTaskRepository.deleteById(id);
    }

    public void deleteTask(Long id){
        Task task = iTaskRepository.findById(id).orElseThrow();

        if(task.getStatus() != TaskStatus.COMPLETED)
            throw new IllegalStateException("Only completed tasks can be deleted");
        iTaskRepository.delete(task);
    }
}
