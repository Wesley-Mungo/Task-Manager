package com.taskmanager.repository;

import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    
    List<Task> findByUserAndStatus(User user, Task.TaskStatus status);
    
    List<Task> findByUserAndPriority(User user, Task.TaskPriority priority);
    
    @Query("SELECT t FROM Task t WHERE t.user = :user AND " +
           "(LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Task> searchTasks(@Param("user") User user, @Param("query") String query);
    
    @Query("SELECT t FROM Task t WHERE t.user = :user AND " +
           "t.status = :status AND " +
           "(LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(t.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Task> searchTasksByStatus(@Param("user") User user, 
                                    @Param("status") Task.TaskStatus status, 
                                    @Param("query") String query);
}



