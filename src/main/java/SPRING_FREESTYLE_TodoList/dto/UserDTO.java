package SPRING_FREESTYLE_TodoList.dto;

import SPRING_FREESTYLE_TodoList.model.TodoList;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public record UserDTO(Long id, String username, String email,
                      @NotBlank
                      String password, List<TodoList> todoLists) {

}
