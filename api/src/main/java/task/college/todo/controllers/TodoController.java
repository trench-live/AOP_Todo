package task.college.todo.controllers;

import org.springframework.web.bind.annotation.*;
import task.college.todo.aop.TrackTime;
import task.college.todo.models.Todo;
import task.college.todo.services.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    @TrackTime
    public List<Todo> getAll() {
        return service.getAllTodos();
    }

    @PostMapping
    @TrackTime
    public Todo create(@RequestBody Todo todo) {
        return service.createTodo(todo);
    }

    @PutMapping("/{id}")
    @TrackTime
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        return service.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    @TrackTime
    public void delete(@PathVariable Long id) {
        service.deleteTodo(id);
    }
}