package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import module.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "Task Management",
        description = "Operations for managing tasks"
)

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Task found"),
        @ApiResponse(responseCode = "404", description = "Task not found")
})
@RestController //componenta care primeste request-uri HTTP
@RequestMapping("/tasks")
// scriu asa ca sa nu mai scriu in toate metodele "/tasks" ... toate endpoint urile incep cu /tasks
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //RequestBody cand vreau sa iau din body ul de pe Postman
    @Operation(
            summary = "Create a task",
            description = "Creates a task by RequestBody and adds it in database"
    )
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse task = taskService.createTask(taskRequest);
        return ResponseEntity.created(URI.create("/tasks/" + task.getId())).body(task);
    }

    @Operation(
            summary = "Get task by id",
            description = "Returns a task using it's unique identifier"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @Operation(
            summary = "Get all tasks",
            description = "Returns all the tasks that are in the database"
    )
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Get task by status identifier",
            description = "Returns a task that it's stored in database"
    )
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> findByStatus(@PathVariable TaskStatus status) {
        List<TaskResponse> tasks = taskService.findByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Get task by priority identifier",
            description = "Returns a task that it's stored in database"
    )
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> findByTaskPriority(@PathVariable TaskPriority priority) {
        List<TaskResponse> tasks = taskService.findByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Get task by archived identifier",
            description = "Returns a task that it's stored in database"
    )
    @GetMapping("/archived/{archived}")
    public ResponseEntity<List<TaskResponse>> findByArchived(@PathVariable boolean archived) {
        List<TaskResponse> tasks = taskService.findByArchived(archived);
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Get task by dateTime identifier",
            description = "Returns a task that it's stored in database"
    )
    @GetMapping("/due-before/{dateTime}")
    public ResponseEntity<List<TaskResponse>> findByDueDateBefore(@PathVariable LocalDateTime dateTime) {
        List<TaskResponse> tasks = taskService.findByDueDateBefore(dateTime);
        return ResponseEntity.ok(tasks); //pot scrie si desfasurat
    }

    @Operation(
            summary = "Get task by title identifier",
            description = "Returns a task that it's stored in database"
    )
    @GetMapping("/search/{title}")
    public ResponseEntity<List<TaskResponse>> findByTitleContaining(@PathVariable String title) {
        return ResponseEntity.ok(taskService.findByTitleContaining(title)); //pot scrie si compact
    }

    @Operation(
            summary = "Updates task by id identifier",
            description = "It just updates the task that it's stored in database"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTask(id, taskRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete task by id identifier",
            description = "Deletes a task that it's stored in database"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete task by id identifier",
            description = "Deletes the task's reference"
    )
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
