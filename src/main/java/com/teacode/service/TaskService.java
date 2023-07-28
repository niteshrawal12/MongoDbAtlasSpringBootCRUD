package com.teacode.service;

import com.teacode.entity.Task;
import com.teacode.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }
    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }
    public Task getTaskByTaskId(String taskId){
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        return optionalTask.orElse(null);
    }
    public List<Task> getTaskBySeverity(int severity){
        return taskRepository.findBySeverity(severity);
    }
    public List<Task> getTaskByAssignee(String assignee){
        return taskRepository.getTasksByAssignee(assignee);
    }
    public Task updateTask(Task taskRequest) {
        Task existingTask = taskRepository.findById(taskRequest.getTaskId())
                .orElseThrow(() -> new NoSuchElementException("Task not found with id: " + taskRequest.getTaskId()));
        existingTask.setAssigne(taskRequest.getAssigne());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        existingTask.setSeverity(taskRequest.getSeverity());

        return taskRepository.save(existingTask);
    }
    public String deleteTask(String taskId){
        taskRepository.deleteById(taskId);
        return taskId+"task deleted from dashboard";
    }
}
