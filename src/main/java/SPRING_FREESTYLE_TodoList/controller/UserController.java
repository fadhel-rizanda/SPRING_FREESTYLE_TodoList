package SPRING_FREESTYLE_TodoList.controller;

import SPRING_FREESTYLE_TodoList.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import SPRING_FREESTYLE_TodoList.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDTO> getUsers(@RequestParam("page") int page,
                                  @RequestParam("size") int size) {
        return userService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/search")
    public Page<UserDTO> getUserByUsername(@RequestParam("username") String username,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size) {
        return userService.findByUsername(username, page, size);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
    @PostMapping("/login")
    public String login(@RequestBody @Valid UserDTO userDTO) {
        return userService.verify(userDTO);
    }

    @PostMapping("/register")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    public Optional<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id, @RequestParam String password) {
        if(userService.deleteUser(id, password))
            System.out.println("User deleted successfully");
        else
            System.out.println("User could not be deleted");
    }
}
