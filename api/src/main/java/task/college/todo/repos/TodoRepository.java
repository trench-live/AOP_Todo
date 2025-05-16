package task.college.todo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import task.college.todo.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
