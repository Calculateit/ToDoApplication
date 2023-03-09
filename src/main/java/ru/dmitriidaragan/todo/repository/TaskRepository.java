package ru.dmitriidaragan.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dmitriidaragan.todo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {}
