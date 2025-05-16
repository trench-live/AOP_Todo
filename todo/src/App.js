import React, { useState } from 'react';
import TodoList from './components/TodoList';
import TodoForm from './components/TodoForm';
import './styles/App.css';

function App() {
  const [refresh, setRefresh] = useState(false);

  return (
      <div className="App">
        <h1>🌈 Cosmic Todos</h1>
        <TodoForm onSuccess={() => setRefresh(!refresh)} />
        <TodoList refresh={refresh} />
      </div>
  );
}

export default App;