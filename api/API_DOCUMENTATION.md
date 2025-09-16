# Документация к API Todo List
## Базовый URL
`http://localhost:8080/tasks`
### Эндпоинты
#### 1. Создание задачи
```
curl -X POST "http://localhost:8080/tasks" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Купить молоко",
    "description": "2 литра",
    "completed": false
  }'
```
**Ответ (201 Created):**
```
{
  "id": 1,
  "title": "Купить молоко",
  "description": "2 литра",
  "completed": false
}
```
**Ошибки:**
- `400 Bad Request` - если title отсутствует или пустой

#### 2. Получение списка задач
##### Все задачи
```
curl -X GET "http://localhost:8080/tasks"
```
##### Только завершенные задачи
```
curl -X GET "http://localhost:8080/tasks?completed=true"
```
##### Только активные задачи
```
curl -X GET "http://localhost:8080/tasks?completed=false"
```

**Ответ (200 OK):**
```
[
  {
    "id": 1,
    "title": "Купить молоко",
    "description": "2 литра",
    "completed": false
  }
]
```

#### 3. Получение конкретной задачи
```
curl -X GET "http://localhost:8080/tasks/1"
```

**Ответ (200 OK):**
```
{
  "id": 1,
  "title": "Купить молоко",
  "description": "2 литра",
  "completed": false
}
```

**Ошибки:**
- `404 Not Found` - если задача не найдена

```
curl -X GET "http://localhost:8080/tasks/999"
```
#### 4. Обновление задачи
```
curl -X PUT "http://localhost:8080/tasks/1" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Купить молоко и хлеб",
    "completed": true
  }'
```

**Ответ (200 OK):**
```
{
  "id": 1,
  "title": "Купить молоко и хлеб",
  "description": "2 литра",
  "completed": true
}
```
**Ошибки:**
- `400 Bad Request` - если передан пустой title
- `404 Not Found` - если задача не найдена
#### 5. Удаление задачи
```
curl -X DELETE "http://localhost:8080/tasks/1"
```

**Ответ:** `204 No Content` (тело ответа отсутствует)

**Ошибки:**
- `404 Not Found` - если задача не найдена

---