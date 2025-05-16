package task.college.todo.services;

import org.springframework.stereotype.Service;
import task.college.todo.models.Todo;
import task.college.todo.repos.TodoRepository;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    public Todo updateTodo(Long id, Todo todo) {
        Todo existingTodo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (todo.getTitle() != null) {
            existingTodo.setTitle(todo.getTitle());
        }

        existingTodo.setDescription(todo.getDescription());
        existingTodo.setCompleted(todo.isCompleted());

        return repository.save(existingTodo);
    }

    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}