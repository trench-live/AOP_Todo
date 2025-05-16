import React, { useState } from 'react';
import { updateTodo } from '../services/todoService';
import '../styles/EditTodoForm.css';

const EditTodoForm = ({ todo, onCancel, onSuccess }) => {
    const [title, setTitle] = useState(todo.title);
    const [description, setDescription] = useState(todo.description);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await updateTodo(todo.id, { ...todo, title, description });
            onSuccess();
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="edit-form">
            <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                className="edit-input"
                required
            />
            <textarea
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                className="edit-textarea"
            />
            <div className="edit-buttons">
                <button type="submit" className="save-btn">💾 Сохранить</button>
                <button type="button" onClick={onCancel} className="cancel-btn">🚫 Отмена</button>
            </div>
        </form>
    );
};

export default EditTodoForm;