package SPRING_FREESTYLE_TodoList.dto;

import SPRING_FREESTYLE_TodoList.model.Users;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TodoListDTO (Long id, Users user, String title, String description, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime excecute_at, String status) {
}
