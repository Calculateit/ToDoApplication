package ru.dmitriidaragan.todo.service;

import ru.dmitriidaragan.todo.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    void add(Task task);
    void delete(Task task);
    Optional<Task> get(Long id);
    @SuppressWarnings("unused")
    Task editTask(Task task);
    List<Task> getAll();
    void setTaskState(Long id, Boolean isChecked);
}
