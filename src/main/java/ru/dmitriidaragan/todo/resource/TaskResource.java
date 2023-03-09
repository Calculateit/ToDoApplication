package ru.dmitriidaragan.todo.resource;

import jakarta.annotation.Resource;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBElement;
import org.springframework.web.bind.annotation.*;
import ru.dmitriidaragan.todo.entity.Task;
import ru.dmitriidaragan.todo.service.TaskService;

@Resource
@RequestMapping("/tasks")
public class TaskResource {
    private final TaskService taskService;
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    @Produces(MediaType.TEXT_XML)
    public Task getTask(@PathVariable Long id) {
        return taskService.get(id);
    }

    @PutMapping
    @Consumes(MediaType.TEXT_XML)
    public Response putTask(JAXBElement<Task> task) {
        taskService.add(task.getValue());
        return Response.ok().build();
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(taskService.get(id));
    }

    @GetMapping
    @Produces({MediaType.TEXT_XML})
    public Response getAllTasks() {
        return Response.ok(taskService.getAll()).build();
    }
}
