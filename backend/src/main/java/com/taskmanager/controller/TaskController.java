package com.taskmanager.controller;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import com.taskmanager.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    private Long getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return jwtUtil.extractUserId(token);
    }
    
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest, 
                                                   HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        TaskResponse response = taskService.createTask(userId, taskRequest);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        List<TaskResponse> tasks = taskService.getAllTasks(userId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long taskId, 
                                                   HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        TaskResponse task = taskService.getTaskById(taskId, userId);
        return ResponseEntity.ok(task);
    }
    
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long taskId, 
                                                   @RequestBody TaskRequest taskRequest,
                                                   HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        TaskResponse updatedTask = taskService.updateTask(taskId, userId, taskRequest);
        return ResponseEntity.ok(updatedTask);
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, 
                                           HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/filter/status")
    public ResponseEntity<List<TaskResponse>> filterByStatus(@RequestParam Task.TaskStatus status,
                                                             HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        List<TaskResponse> tasks = taskService.filterTasksByStatus(userId, status);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/filter/priority")
    public ResponseEntity<List<TaskResponse>> filterByPriority(@RequestParam Task.TaskPriority priority,
                                                               HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        List<TaskResponse> tasks = taskService.filterTasksByPriority(userId, priority);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam String query,
                                                          HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        List<TaskResponse> tasks = taskService.searchTasks(userId, query);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/search/status")
    public ResponseEntity<List<TaskResponse>> searchTasksByStatus(@RequestParam Task.TaskStatus status,
                                                                  @RequestParam String query,
                                                                  HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        List<TaskResponse> tasks = taskService.searchTasksByStatus(userId, status, query);
        return ResponseEntity.ok(tasks);
    }
}



