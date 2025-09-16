package task.college.todo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import task.college.todo.models.Todo;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByCompletedFalse();
    List<Todo> findByCompletedTrue();
}
