package ru.dmitriidaragan.todo.service.implementations;

import org.springframework.stereotype.Service;
import ru.dmitriidaragan.todo.entity.Task;
import ru.dmitriidaragan.todo.repository.TaskRepository;
import ru.dmitriidaragan.todo.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public void add(Task task) {
        taskRepository.saveAndFlush(task);
    }

    @Override
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task get(Long id) {
        return taskRepository.getReferenceById(id);
    }

    @Override
    public Task editTask(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
