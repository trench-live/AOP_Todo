import React, { useState } from 'react';
import EditTodoForm from './EditTodoForm';
import '../styles/TodoItem.css';

const TodoItem = ({ todo, onDelete, onUpdate }) => {
    const [isEditing, setIsEditing] = useState(false);

    const handleUpdateSuccess = () => {
        setIsEditing(false);
        onUpdate(); // Обновляем список задач
    };

    return (
        <div className="todo-item">
            {isEditing ? (
                <EditTodoForm
                    todo={todo}
                    onCancel={() => setIsEditing(false)}
                    onSuccess={handleUpdateSuccess}
                />
            ) : (
                <>
                    <div className="content">
                        <h3 style={{ color: '#FF6B6B' }}>{todo.title}</h3>
                        <p>{todo.description}</p>
                    </div>
                    <div className="controls">
                        <button
                            className="edit-btn"
                            onClick={() => setIsEditing(true)}
                        >
                            ✎
                        </button>
                        <button
                            className="delete-btn"
                            onClick={() => onDelete(todo.id)}
                        >
                            ✕
                        </button>
                    </div>
                </>
            )}
        </div>
    );
};

export default TodoItem;