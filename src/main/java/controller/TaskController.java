package controller;

import module.Task;
import module.TaskRequest;
import org.springframework.web.bind.annotation.*;
import service.TaskService;
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
        return taskService.createTask(taskRequest.getTitle(),taskRequest.getStatus());
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskByID(id);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        taskService.updateTask(id,taskRequest.getTitle(),taskRequest.getStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }

    @DeleteMapping
    public void deleteTask(@RequestBody Task task){
        taskService.deleteTask(task);
    }
}
