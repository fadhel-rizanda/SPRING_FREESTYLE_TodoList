package SPRING_FREESTYLE_TodoList.dto;

import SPRING_FREESTYLE_TodoList.model.TodoList;
import SPRING_FREESTYLE_TodoList.model.Users;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChatDTO(Long id, Users sender, String content, TodoList todoList, LocalDateTime send_at) {
}
