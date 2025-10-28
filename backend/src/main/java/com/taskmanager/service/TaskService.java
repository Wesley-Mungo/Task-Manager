package com.taskmanager.service;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public TaskResponse createTask(Long userId, TaskRequest taskRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setPriority(taskRequest.getPriority());
        task.setDueDate(taskRequest.getDueDate());
        task.setUser(user);
        
        Task savedTask = taskRepository.save(task);
        return convertToResponse(savedTask);
    }
    
    public List<TaskResponse> getAllTasks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return taskRepository.findByUser(user).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public TaskResponse getTaskById(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }
        
        return convertToResponse(task);
    }
    
    public TaskResponse updateTask(Long taskId, Long userId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }
        
        if (taskRequest.getTitle() != null) {
            task.setTitle(taskRequest.getTitle());
        }
        if (taskRequest.getDescription() != null) {
            task.setDescription(taskRequest.getDescription());
        }
        if (taskRequest.getStatus() != null) {
            task.setStatus(taskRequest.getStatus());
        }
        if (taskRequest.getPriority() != null) {
            task.setPriority(taskRequest.getPriority());
        }
        if (taskRequest.getDueDate() != null) {
            task.setDueDate(taskRequest.getDueDate());
        }
        
        Task updatedTask = taskRepository.save(task);
        return convertToResponse(updatedTask);
    }
    
    public void deleteTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        if (!task.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }
        
        taskRepository.delete(task);
    }
    
    public List<TaskResponse> filterTasksByStatus(Long userId, Task.TaskStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return taskRepository.findByUserAndStatus(user, status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> filterTasksByPriority(Long userId, Task.TaskPriority priority) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return taskRepository.findByUserAndPriority(user, priority).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> searchTasks(Long userId, String query) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return taskRepository.searchTasks(user, query).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    public List<TaskResponse> searchTasksByStatus(Long userId, Task.TaskStatus status, String query) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return taskRepository.searchTasksByStatus(user, status, query).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    private TaskResponse convertToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt() != null ? task.getCreatedAt().toLocalDate() : null,
                task.getUpdatedAt() != null ? task.getUpdatedAt().toLocalDate() : null,
                task.getUser().getId()
        );
    }
}



