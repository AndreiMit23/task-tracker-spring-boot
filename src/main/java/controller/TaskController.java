package controller;

import module.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.net.URI;
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
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
        TaskResponse task = taskService.createTask(taskRequest);
        return ResponseEntity.created(URI.create("/tasks/" +task.getId())).body(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){
        TaskResponse task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> findByStatus(@PathVariable TaskStatus status){
        List<TaskResponse> tasks = taskService.findByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> findByTaskPriority(@PathVariable TaskPriority priority){
        List<TaskResponse> tasks = taskService.findByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/archived/{archived}")
    public ResponseEntity<List<TaskResponse>> findByArchived(@PathVariable boolean archived){
        List<TaskResponse> tasks = taskService.findByArchived(archived);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/due-before/{dateTime}")
    public ResponseEntity<List<TaskResponse>> findByDueDateBefore(@PathVariable LocalDateTime dateTime){
        List<TaskResponse> tasks = taskService.findByDueDateBefore(dateTime);
        return ResponseEntity.ok(tasks); //pot scrie si desfasurat
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<TaskResponse>> findByTitleContaining(@PathVariable String title){
        return ResponseEntity.ok(taskService.findByTitleContaining(title)); //pot scrie si compact
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        taskService.updateTask(id,taskRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id){
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
