import React, { useState } from 'react';
import { createTodo } from '../services/todoService';
import '../styles/TodoForm.css';

const TodoForm = ({ onSuccess }) => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createTodo({ title, description, completed: false });
            onSuccess();
            setTitle('');
            setDescription('');
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="todo-form">
            <input
                type="text"
                placeholder="Название задачи"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
            />
            <textarea
                placeholder="Описание"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            />
            <button type="submit" className="add-btn">
                + Добавить
            </button>
        </form>
    );
};

export default TodoForm;