package ru.dmitriidaragan.todo.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ru.dmitriidaragan.todo.entity.Task;
import ru.dmitriidaragan.todo.service.TaskService;

@Resource
@Path("tasks")
public class TaskResource {
    private final TaskService taskService;

    @Inject
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@PathParam("id") Long id) {
        try {
            return Response.ok(new ObjectMapper().writeValueAsString(taskService.get(id).orElse(new Task()))).build();
        } catch (JsonProcessingException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response putTask(@Context HttpHeaders headers) {
        try {
            String value = headers.getHeaderString("newTask");
            Task task = new ObjectMapper().readValue(value, Task.class);
            taskService.add(task);
            return Response.ok().build();
        } catch (JsonProcessingException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("{id}")
    @DELETE
    public Response deleteTask(@PathParam("id") Long id) {
        taskService.delete(taskService.get(id).orElse(null));
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTasks() {
        return Response.ok(taskService.getAll()).build();
    }

    @POST
    @Path("{id}")
    public Response setTaskState(@PathParam("id") Long id, @QueryParam("checked") Boolean isChecked) {
        taskService.setTaskState(id, isChecked);
        return Response.ok().build();
    }
}
