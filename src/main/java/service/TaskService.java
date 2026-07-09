package service;

import mapper.TaskMapper;
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
    private final TaskMapper taskMapper;

    public TaskService(ITaskRepository iTaskRepository, TaskMapper taskMapper) {
        this.iTaskRepository = iTaskRepository;
        this.taskMapper = taskMapper;
    }


    public TaskResponse createTask(TaskRequest taskRequest) {

//        long id = repository.getSize();
//        String id = UUID.randomUUID().toString();
//         iTaskRepository.count();

        if (taskRequest.getTitle() == null || taskRequest.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        Task task = taskMapper.toEntity(taskRequest);

        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        iTaskRepository.save(task);

        return taskMapper.toResponse(task);
    }

    public TaskResponse getTaskByID(Long id) {
        Task task = iTaskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id " + id + " not found"));
        return taskMapper.toResponse(task);
    }

    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = iTaskRepository.findAll();

        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByStatus(TaskStatus status) {
        List<Task> tasks = iTaskRepository.findByStatus(status);
        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByPriority(TaskPriority taskPriority) {
        if (taskPriority == null)
            throw new IllegalStateException("Priority is required");
        List<Task> tasks = iTaskRepository.findByTaskPriority(taskPriority);

        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByArchived(boolean archived) {
        List<Task> tasks = iTaskRepository.findByArchived(archived);

        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByDueDateBefore(LocalDateTime dateTime) {

        if (dateTime == null)
            throw new IllegalStateException("Date is required");
        List<Task> tasks = iTaskRepository.findByDueDateBefore(dateTime);

        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByTitleContaining(String title) {
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException("Title is required");
        List<Task> tasks = iTaskRepository.findByTitleContaining(title);

        return taskMapper.toResponseList(tasks);
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
