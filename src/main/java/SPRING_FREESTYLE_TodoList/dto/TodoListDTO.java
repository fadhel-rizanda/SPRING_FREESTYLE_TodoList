package SPRING_FREESTYLE_TodoList.dto;

import SPRING_FREESTYLE_TodoList.model.Users;

import java.time.LocalDateTime;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TodoListDTO (Long id, Users user, String title, String description, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime excecute_at, String status) {
}
