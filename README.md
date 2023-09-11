# ToDoList
ToDoList is a task management app.

Postman:

Task:
  
    CreateTask:
      Post http://localhost:9090/api/todos/{todo-id}/tasks
        JSON
        {
            "name": "Task #5-1",
            "priority": "HIGH",
            "todoId": 7,
            "stateId": 8
        }
    
    ReadTask:
      GET http://localhost:9090/api/todos/{todo-id}/tasks/{task-id}
    
    UpdateTask:
      PUT http://localhost:9090/api/todos/{todo-id}/tasks/{task-id}
        JSON
        {
          "id": 5,
          "name": "Task #1-1",
          "priority": "HIGH",
          "todoId": 7,
          "stateId": 8 
        }

    DeleteTask
      DELETE ttp://localhost:9090/api/todos/{todo-id}/tasks/{task-id}

    GetAllTasks
      GET http://localhost:9090/api/todos/{todo-id}/tasks

User:

    CreateUser:
      POST http://localhost:9090/api/users
        JSON
        {
          "firstName": "Tests",
          "lastName": "Tests",
          "email": "tests@mail.com",
          "password": "11111",
          "roleId": 2
        }

    ReadUser
      GET http://localhost:9090/api/users/{user-id}

    UpdateUser
      PUT http://localhost:9090/api/users/{user-id}
        JSON
        {
          "id": 10,
          "firstName": "Testss",
          "lastName": "Testss",
          "email": "testss@mail.com",
          "password": "111111",
          "roleId": 2
        }

    DeleteUser
      DELETE http://localhost:9090/api/users/{user-id}

    GetAllUsers
      GET http://localhost:9090/api/users

ToDo:

    CreateToDo
      POST http://localhost:9090/api/users/{user-id}/todos
        JSON
          {
            "title": "Test",
            "createdAt": "2020-09-16T14:00:04.810221",
            "owner": 4
          }

    ReadToDo
     GET http://localhost:9090/api/users/{user-id}/todos/{todo-id}

    UpdateToDo
      PUT http://localhost:9090/api/users/{user-id}/todos/{todo-id}
        JSON
          {
            "id": 8,
            "title": "Test2.1",
            "createdAt": "2020-09-16T14:00:04.810221",
            "owner": 4
          }

    DeleteToDo
      DELETE http://localhost:9090/api/users/{user-id}/todos/{todo-id}

    GetAllTodos
      GET http://localhost:9090/api/users/{user-id}/todos
    
