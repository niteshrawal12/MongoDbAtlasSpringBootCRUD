package com.teacode.controller;

import com.teacode.entity.Task;
import com.teacode.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService mockTaskService;

    @Test
    void testCreateTask() throws Exception {
        // Setup
        // Configure TaskService.addTask(...).
        final Task task = new Task("taskId", "description", 0, "assigne", 0);
        when(mockTaskService.addTask(new Task("taskId", "description", 0, "assigne", 0))).thenReturn(task);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/tasks")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetTasks() throws Exception {
        // Setup
        // Configure TaskService.findAllTask(...).
        final List<Task> tasks = List.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskService.findAllTask()).thenReturn(tasks);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetTasks_TaskServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTaskService.findAllTask()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetTask() throws Exception {
        // Setup
        // Configure TaskService.getTaskByTaskId(...).
        final Task task = new Task("taskId", "description", 0, "assigne", 0);
        when(mockTaskService.getTaskByTaskId("taskId")).thenReturn(task);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks/{taskId}", "taskId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindTaskBySeverity() throws Exception {
        // Setup
        // Configure TaskService.getTaskBySeverity(...).
        final List<Task> tasks = List.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskService.getTaskBySeverity(0)).thenReturn(tasks);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks/severity/{severity}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindTaskBySeverity_TaskServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTaskService.getTaskBySeverity(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks/severity/{severity}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetTaskByAssignee() throws Exception {
        // Setup
        // Configure TaskService.getTaskByAssignee(...).
        final List<Task> tasks = List.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskService.getTaskByAssignee("assignee")).thenReturn(tasks);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks/assignee/{assignee}", "assignee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetTaskByAssignee_TaskServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTaskService.getTaskByAssignee("assignee")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tasks/assignee/{assignee}", "assignee")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testModifyTask() throws Exception {
        // Setup
        // Configure TaskService.updateTask(...).
        final Task task = new Task("taskId", "description", 0, "assigne", 0);
        when(mockTaskService.updateTask(new Task("taskId", "description", 0, "assigne", 0))).thenReturn(task);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/tasks")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDeleteTask() throws Exception {
        // Setup
        when(mockTaskService.deleteTask("taskId")).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/tasks/{taskId}", "taskId")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
