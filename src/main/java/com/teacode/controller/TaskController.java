package com.teacode.controller;

import com.teacode.entity.Task;
import com.teacode.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return taskService.addTask(task);
    }
    @GetMapping
    public List<Task> getTasks(){
        return taskService.findAllTask();
    }
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId){
        return taskService.getTaskByTaskId(taskId);
    }
    @GetMapping("/severity/{severity}")
    public List<Task> findTaskBySeverity(@PathVariable  int severity){
        return taskService.getTaskBySeverity(severity);
    }
    @GetMapping("/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee){
        return  taskService.getTaskByAssignee(assignee);
    }
    @PutMapping
    public Task modifyTask(@RequestBody Task task){
        return taskService.updateTask(task);
    }
    @PatchMapping("/{id}")
    public Task updateTaskFields(@PathVariable String id,@RequestBody Map<String, Object> fields){
        return taskService.updateTaskByFields(id,fields);
    }


    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return taskService.deleteTask(taskId);
    }

}
