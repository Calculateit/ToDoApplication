package ru.dmitriidaragan.todo.service;

import ru.dmitriidaragan.todo.entity.Task;

import java.util.List;

public interface TaskService {
    void add(Task task);
    void delete(Task task);
    Task get(Long id);
    @SuppressWarnings("unused")
    Task editTask(Task task);
    List<Task> getAll();
}
