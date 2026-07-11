package service;

import mapper.TaskMapper;
import module.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.ITaskRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service //componenta care tine logica de business
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final ITaskRepository iTaskRepository;
    private final TaskMapper taskMapper;

    public TaskService(ITaskRepository iTaskRepository, TaskMapper taskMapper) {
        this.iTaskRepository = iTaskRepository;
        this.taskMapper = taskMapper;
    }


    public TaskResponse createTask(TaskRequest taskRequest) {

        logger.info("Creating task with title '{}'", taskRequest.getTitle());

        if (taskRequest.getTitle() == null || taskRequest.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }

        Task task = taskMapper.toEntity(taskRequest);

        iTaskRepository.save(task);

        logger.info("Task created successfully with id '{}'", task.getId());

        return taskMapper.toResponse(task);
    }

    public TaskResponse getTaskByID(Long id) {
        Task task = iTaskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task with id " + id + " not found"));
        return taskMapper.toResponse(task);
    }

    public List<TaskResponse> getAllTasks() {
        logger.info("Finding all tasks...");
        List<Task> tasks = iTaskRepository.findAll();

        logger.info("Tasks found successfully");
        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByStatus(TaskStatus status) {
        logger.info("Finding task by status {}", status);
        List<Task> tasks = iTaskRepository.findByStatus(status);

        logger.info("Task by status {}, found successfully", status);
        return taskMapper.toResponseList(tasks);
    }

    public List<TaskResponse> findByPriority(TaskPriority taskPriority) {
        if (taskPriority == null) {
            logger.error("Finding task by priority requires parameter");
            throw new IllegalStateException("Priority is required");
        }
        logger.info("Finding task by priority {}", taskPriority);
        List<Task> tasks = iTaskRepository.findByTaskPriority(taskPriority);
        logger.info("Task by priority {} was found",taskPriority);

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

        logger.info("Updating task {}", id);
        Task task = iTaskRepository.findById(id).orElseThrow();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setTaskPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());

        iTaskRepository.save(task);
        logger.info("Task {} updated successfully", id);
    }

    public void deleteTaskById(Long id) {
        iTaskRepository.deleteById(id);
    }

    public void deleteTask(Long id) {
        Task task = iTaskRepository.findById(id).orElseThrow();

        if (task.getStatus() != TaskStatus.COMPLETED) {
            logger.warn("Attempt to delete task {} that is not completed", id);
            throw new IllegalStateException("Only completed tasks can be deleted");
        }
        iTaskRepository.delete(task);
    }
}
