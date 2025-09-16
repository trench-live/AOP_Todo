package task.college.todo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.college.todo.aop.TrackTime;
import task.college.todo.models.Todo;
import task.college.todo.services.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping
    @TrackTime
    public ResponseEntity<Todo> create(@Valid @RequestBody Todo todo) {
        Todo created = todoService.create(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @TrackTime
    public ResponseEntity<List<Todo>> getAll(
            @RequestParam(name = "completed") Optional<Boolean> completed) {
        List<Todo> todos = todoService.getAll(completed);
        return ResponseEntity.ok(todos);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Todo> getById(@PathVariable long id) {
        Todo todo = todoService.getById(id);
        return todo != null ? ResponseEntity.ok(todo) :
                              ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @TrackTime
    public ResponseEntity<Todo> update(@Valid @PathVariable Long id, @RequestBody Todo todo) {
        Todo updated = todoService.update(id, todo);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @TrackTime
    public ResponseEntity<Todo> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}