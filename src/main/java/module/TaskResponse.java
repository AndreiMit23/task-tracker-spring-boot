package module;

import java.time.LocalDateTime;

public class TaskResponse {
    private Long id;
    private String title;
    private TaskStatus status;
    private String description;
    private TaskPriority priority;
    private LocalDateTime dueDate;

    public TaskResponse(Long id, String title, TaskStatus status,String description, TaskPriority priority, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
