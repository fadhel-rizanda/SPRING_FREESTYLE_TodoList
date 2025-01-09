package SPRING_FREESTYLE_TodoList.dto;

import SPRING_FREESTYLE_TodoList.model.TodoList;
import SPRING_FREESTYLE_TodoList.model.Users;

import java.time.LocalDateTime;

//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChatDTO(Long id, Users sender, String content, TodoList todoList, LocalDateTime send_at) {
}
