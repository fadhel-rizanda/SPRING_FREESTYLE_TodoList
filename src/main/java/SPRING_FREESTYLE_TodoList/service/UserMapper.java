package SPRING_FREESTYLE_TodoList.service;

import SPRING_FREESTYLE_TodoList.dto.UserDTO;
import SPRING_FREESTYLE_TodoList.dto.UserRegisterDTO;
import SPRING_FREESTYLE_TodoList.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static UserDTO toUserDTO(Users user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getTodoLists());
        return userDTO;
    }
    public static UserRegisterDTO toUserRegisterDTO(Users user) {
        UserRegisterDTO userDTO = new UserRegisterDTO(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
        return userDTO;
    }

    public static Users toUserEntity(UserDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setTodoLists(userDTO.todoLists());

        return user;
    }

    public static Users toUserEntity(UserRegisterDTO userDTO) {
        Users user = new Users();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.roles());

        return user;
    }
}
