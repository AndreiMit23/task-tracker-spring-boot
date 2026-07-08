package controller;

import module.Task;
import module.TaskPriority;
import module.TaskRequest;
import module.TaskStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.time.LocalDateTime;
import java.util.List;


@RestController //componenta care primeste request-uri HTTP
@RequestMapping("/tasks") // scriu asa ca sa nu mai scriu in toate metodele "/tasks" ... toate endpoint urile incep cu /tasks
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //RequestBody cand vreau sa iau din body ul de pe Postman
    @PostMapping
    public Task createTask(@RequestBody TaskRequest taskRequest){
        return taskService.createTask(taskRequest);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskByID(id);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> findByStatus(@PathVariable TaskStatus status){
        return taskService.findByStatus(status);
    }

    @GetMapping("/priority/{priority}")
    public List<Task> findByTaskPriority(@PathVariable TaskPriority priority){
        return taskService.findByPriority(priority);
    }

    @GetMapping("/archived/{archived}")
    public List<Task> findByArchived(@PathVariable boolean archived){
        return taskService.findByArchived(archived);
    }

    @GetMapping("/due-before/{dateTime}")
    public List<Task> findByDueDateBefore(@PathVariable LocalDateTime dateTime){
        return taskService.findByDueDateBefore(dateTime);
    }

    @GetMapping("/search/{title}")
    public List<Task> findByTitleContaining(@PathVariable String title){
        return taskService.findByTitleContaining(title);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        taskService.updateTask(id,taskRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
