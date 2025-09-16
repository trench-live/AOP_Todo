package task.college.todo.services;

import org.springframework.stereotype.Service;
import task.college.todo.exceptions.TodoNotFoundException;
import task.college.todo.models.Todo;
import task.college.todo.repos.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAll(Optional<Boolean> completed) {
        if (completed.isPresent()) {
            if (completed.get())
                return repository.findByCompletedTrue();
            return repository.findByCompletedFalse();
        }
        return repository.findAll();
    }

    public Todo getById(long id) {
        return repository.findById(id).orElse(null);
    }

    public Todo create(Todo todo) {
        if (todo.getTitle() == null || todo.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        return repository.save(todo);
    }

    public Todo update(Long id, Todo todo) {
        Todo existingTodo = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Task not found"));

        if (todo.getTitle() != null) {
            if (todo.getTitle().trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }
            existingTodo.setTitle(todo.getTitle());
        }

        existingTodo.setDescription(todo.getDescription());
        existingTodo.setCompleted(todo.isCompleted());
        return repository.save(existingTodo);
    }

    public void delete(Long id) {
        if (repository.findById(id).isEmpty())
            throw new TodoNotFoundException("Task not found");
        repository.deleteById(id);
    }
}