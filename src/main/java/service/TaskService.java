package service;

import module.*;
import org.springframework.stereotype.Service;
import repository.ITaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service //componenta care tine logica de business
public class TaskService {

    private final ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus(), task.getDescription(), task.getTaskPriority(), task.getDueDate());
    }

    private List<TaskResponse> mapToResponseList(List<Task> tasks){
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            taskResponses.add(mapToResponse(task));
        }

        return taskResponses;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {

//        long id = repository.getSize();
//        String id = UUID.randomUUID().toString();
//         iTaskRepository.count();

        if (taskRequest.getTitle() == null || taskRequest.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        Task task = new Task(taskRequest.getTitle(), taskRequest.getDescription(), taskRequest.getPriority(), taskRequest.getDueDate());

        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        iTaskRepository.save(task);

        return mapToResponse(task);
    }

    public TaskResponse getTaskByID(Long id) {
        Task task = iTaskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id " + id + " not found"));
        return mapToResponse(task);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = iTaskRepository.findAll();

        return mapToResponseList(tasks);
    }

    public List<TaskResponse> findByStatus(TaskStatus status) {
        List<Task> tasks = iTaskRepository.findByStatus(status);
        return mapToResponseList(tasks);
    }

    public List<TaskResponse> findByPriority(TaskPriority taskPriority) {
        if (taskPriority == null)
            throw new IllegalStateException("Priority is required");
        List<Task> tasks = iTaskRepository.findByTaskPriority(taskPriority);

        return mapToResponseList(tasks);
    }

    public List<TaskResponse> findByArchived(boolean archived) {
        List<Task> tasks = iTaskRepository.findByArchived(archived);

        return mapToResponseList(tasks);
    }

    public List<TaskResponse> findByDueDateBefore(LocalDateTime dateTime) {

        if (dateTime == null)
            throw new IllegalStateException("Date is required");
        List<Task> tasks = iTaskRepository.findByDueDateBefore(dateTime);

        return mapToResponseList(tasks);
    }

    public List<TaskResponse> findByTitleContaining(String title) {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Title is required");
        List<Task> tasks = iTaskRepository.findByTitleContaining(title);

        return mapToResponseList(tasks);
    }

    public void updateTask(Long id, TaskRequest taskRequest) {

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

    public void deleteTask(Long id) {
        Task task = iTaskRepository.findById(id).orElseThrow();

        if (task.getStatus() != TaskStatus.COMPLETED)
            throw new IllegalStateException("Only completed tasks can be deleted");
        iTaskRepository.delete(task);
    }
}
