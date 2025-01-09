package SPRING_FREESTYLE_TodoList.service;

import SPRING_FREESTYLE_TodoList.dto.TodoListDTO;
import SPRING_FREESTYLE_TodoList.model.TodoList;

public class TodoListMapper {
    public static TodoListDTO toTodoListDTO(TodoList todoList){
        TodoListDTO todoListDTO = new TodoListDTO(todoList.getId(), todoList.getUser(), todoList.getTitle(), todoList.getDescription(), todoList.getCreated_at(), todoList.getUpdated_at(), todoList.getExcecute_at(), todoList.getStatus());
        return todoListDTO;
    }

    public static  TodoList toTodoListEntity(TodoListDTO todoListDTO){
        TodoList todoList = new TodoList();
        todoList.setUser(todoListDTO.user());
        todoList.setTitle(todoListDTO.title());
        todoList.setDescription(todoListDTO.description());
        todoList.setCreated_at(todoListDTO.created_at());
        todoList.setUpdated_at(todoListDTO.updated_at());
        todoList.setExcecute_at(todoListDTO.excecute_at());
        todoList.setStatus(todoListDTO.status());

        return todoList;
    }
}
