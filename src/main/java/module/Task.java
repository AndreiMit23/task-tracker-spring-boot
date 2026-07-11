package module;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static module.TaskStatus.PENDING;

//!Task is used for data storing
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    boolean archived = false;


    @Enumerated(EnumType.STRING) //in baza de date JPA salveaza ca fiind STRING urile setate, nu 0 1 2...
    TaskStatus status = PENDING; //default status PENDING

    @Enumerated(EnumType.STRING)
    TaskPriority taskPriority = TaskPriority.MEDIUM;

    @Column(name = "created_at")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt;

    LocalDateTime dueDate;

    public Task(String titleTask, String description, TaskPriority priority, LocalDateTime dueDate) {
        this.title = titleTask;
        this.description = description;
        this.dueDate = dueDate;
        this.taskPriority = priority;
    }

    public Task() {

    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
