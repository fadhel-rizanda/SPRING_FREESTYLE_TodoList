package SPRING_FREESTYLE_TodoList.controller;

import SPRING_FREESTYLE_TodoList.dto.TodoListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import SPRING_FREESTYLE_TodoList.service.TodoListService;

@RestController
@RequestMapping("/lists")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public Page<TodoListDTO> getLists(@RequestParam("page") int page,
                                      @RequestParam("size") int size){
        return todoListService.findAll(page, size);
    }

    @GetMapping("/:{id}")
    public  TodoListDTO getListById(@PathVariable Long id){
        return todoListService.findById(id);
    }

    @GetMapping("/title")
    public Page<TodoListDTO> getListByTitle(@RequestParam String title,
                                            @RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        return todoListService.findByTitle(title, page, size);
    }

    @GetMapping("/user/{id}")
    public Page<TodoListDTO> getListByUser(@PathVariable Long id,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size){
        return todoListService.findByUser(id, page, size);
    }

    @PostMapping
    public TodoListDTO createList(@RequestBody TodoListDTO todoListDTO){
        return todoListService.addList(todoListDTO);
    }

    @PutMapping
    public TodoListDTO updateList(@RequestBody TodoListDTO todoListDTO){
        return todoListService.updateTodoList(todoListDTO);
    }

    @DeleteMapping
    public void deleteList(@RequestParam("id") Long id){
        todoListService.deleteTodoList(id);
    }

}
