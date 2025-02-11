package SPRING_FREESTYLE_TodoList.dto;

import SPRING_FREESTYLE_TodoList.model.TodoList;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserRegisterDTO(String username, String email,
                              @NotBlank String password, String roles) {

}
