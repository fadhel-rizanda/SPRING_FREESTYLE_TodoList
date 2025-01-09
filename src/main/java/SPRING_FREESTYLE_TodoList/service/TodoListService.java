package SPRING_FREESTYLE_TodoList.service;

import SPRING_FREESTYLE_TodoList.dto.TodoListDTO;
import SPRING_FREESTYLE_TodoList.model.TodoList;
import SPRING_FREESTYLE_TodoList.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import SPRING_FREESTYLE_TodoList.repository.TodoListRepository;
import SPRING_FREESTYLE_TodoList.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TodoListService {;
    @Autowired
    private TodoListRepository todoListRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<TodoListDTO> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<TodoList> todoList = todoListRepository.findAll(pageable);
        return todoList.map(TodoListMapper::toTodoListDTO);
    }

    public TodoListDTO findById(Long id){
        TodoList todoList = todoListRepository.findById(id).orElseThrow(() -> new NoSuchElementException("List not found"));
        return  TodoListMapper.toTodoListDTO(todoList);
    }

//    pagination
    public Page<TodoListDTO> findByTitle(String title, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<TodoList> todoListPage = todoListRepository.findByTitleContainingIgnoreCase(title, pageable);
        return todoListPage.map(TodoListMapper::toTodoListDTO);
    }

    public Page<TodoListDTO> findByUser(Long userId, int page, int size){
        Users user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        Pageable pageable = PageRequest.of(page, size);
        Page<TodoList> todoListPage = todoListRepository.findByUser(user, pageable);
        return todoListPage.map(TodoListMapper::toTodoListDTO);
    }


    public TodoListDTO addList(TodoListDTO todoListDTO) {
        TodoList todoList = TodoListMapper.toTodoListEntity(todoListDTO);
        todoListRepository.save(todoList);
        return TodoListMapper.toTodoListDTO(todoList);
    }

    public TodoListDTO updateTodoList(TodoListDTO todoListDTO){
        TodoList todoList = todoListRepository.findById(todoListDTO.id()).orElseThrow(() -> new NoSuchElementException("List not found"));
        todoList.setTitle(todoListDTO.title());
        todoList.setDescription(todoListDTO.description());
        todoList.setUpdated_at(LocalDateTime.now());
        todoList.setExcecute_at(todoListDTO.excecute_at());
        todoList.setStatus(todoListDTO.status());
        todoListRepository.save(todoList);
        return TodoListMapper.toTodoListDTO(todoList);
    }

    public void deleteTodoList(Long id){
        TodoList todoList = todoListRepository.findById(id).orElseThrow(() -> new NoSuchElementException("List not found"));
        todoListRepository.delete(todoList);
    }


//    add, delete, update
}
