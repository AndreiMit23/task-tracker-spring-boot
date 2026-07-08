package repository;

import module.Task;
import module.TaskPriority;
import module.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByTaskPriority(TaskPriority taskPriority);
    List<Task> findByArchived(boolean archived);
    List<Task> findByDueDateBefore(LocalDateTime dateTime);
    List<Task> findByTitleContaining(String title);
}
