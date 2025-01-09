package SPRING_FREESTYLE_TodoList.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/test")
public class TestController {

    @GetMapping
    public String test() {
        return "test";
    }
    @GetMapping("/oauth")
    public String testOauth() {
        return "test oauth";
    }
}
