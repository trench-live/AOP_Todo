import React, { useEffect, useState } from 'react';
import { getTodos, deleteTodo } from '../services/todoService';
import TodoItem from './TodoItem';
import '../styles/TodoList.css';

const TodoList = ({ refresh }) => {
    const [todos, setTodos] = useState([]);

    // Функция для обновления списка задач
    const fetchTodos = async () => {
        try {
            const response = await getTodos();
            setTodos(response.data);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    useEffect(() => {
        fetchTodos();
    }, [refresh]);

    // Обработчик удаления задачи
    const handleDelete = async (id) => {
        try {
            await deleteTodo(id);
            fetchTodos(); // Обновляем список после удаления
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const handleUpdate = () => {
        fetchTodos();
    };

    return (
        <div className="todo-list">
            {todos.map(todo => (
                <TodoItem
                    key={todo.id}
                    todo={todo}
                    onDelete={handleDelete}
                    onUpdate={handleUpdate}
                />
            ))}
        </div>
    );
};

export default TodoList;