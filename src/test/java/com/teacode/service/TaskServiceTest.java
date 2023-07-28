package com.teacode.service;

import com.teacode.entity.Task;
import com.teacode.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository mockTaskRepository;

    private TaskService taskServiceUnderTest;

    @BeforeEach
    void setUp() {
        taskServiceUnderTest = new TaskService(mockTaskRepository);
    }

    @Test
    void testAddTask() {
        // Setup
        final Task task = new Task("taskId", "description", 0, "assigne", 0);

        // Configure TaskRepository.save(...) to return the same task when called.
        when(mockTaskRepository.save(task)).thenReturn(task);

        // Run the test
        final Task result = taskServiceUnderTest.addTask(task);

        // Verify the results
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(task);

        // Verify that the task was saved using ArgumentCaptor
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(mockTaskRepository).save(taskCaptor.capture());

        // Ensure that the captured task argument is not null and matches the expected task
        Task capturedTask = taskCaptor.getValue();
        assertThat(capturedTask).isNotNull();
        assertThat(capturedTask).isEqualTo(task);
    }

    @Test
    void testFindAllTask() {
        // Setup
        final List<Task> expectedResult = List.of(new Task("taskId", "description", 0, "assigne", 0));

        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = List.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskRepository.findAll()).thenReturn(tasks);

        // Run the test
        final List<Task> result = taskServiceUnderTest.findAllTask();

        // Verify the results
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    void testFindAllTask_TaskRepositoryReturnsNoItems() {
        // Setup
        when(mockTaskRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Task> result = taskServiceUnderTest.findAllTask();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTaskByTaskId() {
        // Setup
        final Task expectedResult = new Task("taskId", "description", 0, "assigne", 0);

        // Configure TaskRepository.findById(...).
        final Optional<Task> task = Optional.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskRepository.findById("taskId")).thenReturn(task);

        // Run the test
        final Task result = taskServiceUnderTest.getTaskByTaskId("taskId");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTaskByTaskId_TaskRepositoryReturnsAbsent() {
        // Setup
        when(mockTaskRepository.findById("taskId")).thenReturn(Optional.empty());

        // Run the test
        final Task result = taskServiceUnderTest.getTaskByTaskId("taskId");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetTaskBySeverity() {
        // Setup
        final List<Task> expectedResult = List.of(new Task("taskId", "description", 0, "assigne", 0));

        // Configure TaskRepository.findBySeverity(...).
        final List<Task> tasks = List.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskRepository.findBySeverity(0)).thenReturn(tasks);

        // Run the test
        final List<Task> result = taskServiceUnderTest.getTaskBySeverity(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTaskBySeverity_TaskRepositoryReturnsNoItems() {
        // Setup
        when(mockTaskRepository.findBySeverity(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Task> result = taskServiceUnderTest.getTaskBySeverity(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTaskByAssignee() {
        // Setup
        final List<Task> expectedResult = List.of(new Task("taskId", "description", 0, "assigne", 0));

        // Configure TaskRepository.getTasksByAssignee(...).
        final List<Task> tasks = List.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskRepository.getTasksByAssignee("assignee")).thenReturn(tasks);

        // Run the test
        final List<Task> result = taskServiceUnderTest.getTaskByAssignee("assignee");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTaskByAssignee_TaskRepositoryReturnsNoItems() {
        // Setup
        when(mockTaskRepository.getTasksByAssignee("assignee")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Task> result = taskServiceUnderTest.getTaskByAssignee("assignee");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUpdateTask() {
        // Setup
        final Task taskRequest = new Task("taskId", "description", 0, "assigne", 0);
        final Task expectedResult = new Task("taskId", "description", 0, "assigne", 0);

        // Configure TaskRepository.findById(...).
        final Optional<Task> task = Optional.of(new Task("taskId", "description", 0, "assigne", 0));
        when(mockTaskRepository.findById("taskId")).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task1 = new Task("taskId", "description", 0, "assigne", 0);
        when(mockTaskRepository.save(new Task("taskId", "description", 0, "assigne", 0))).thenReturn(task1);

        // Run the test
        final Task result = taskServiceUnderTest.updateTask(taskRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateTask_TaskRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Task taskRequest = new Task("taskId", "description", 0, "assigne", 0);
        when(mockTaskRepository.findById("taskId")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> taskServiceUnderTest.updateTask(taskRequest))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testDeleteTask() {
        // Setup
        // Run the test
        final String result = taskServiceUnderTest.deleteTask("taskId");

        // Verify the results
        assertThat(result).isEqualTo("taskIdtask deleted from dashboard");
        verify(mockTaskRepository).deleteById("taskId");
    }
}
