package SPRING_FREESTYLE_TodoList.controller;

import SPRING_FREESTYLE_TodoList.dto.ChatDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatDTO sendMessage(@Payload ChatDTO chatDTO) {
        return chatDTO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic.public")
    public ChatDTO addUser(@Payload ChatDTO chatDTO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("user", chatDTO);
        return chatDTO;
    }
}
