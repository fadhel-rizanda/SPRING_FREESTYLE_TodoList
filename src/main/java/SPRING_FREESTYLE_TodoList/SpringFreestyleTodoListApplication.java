package SPRING_FREESTYLE_TodoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringFreestyleTodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFreestyleTodoListApplication.class, args);
	}

//	Command Line Runner, tidak perlu ditulis karena jpa akan secara otomatis menajdi ORM-nya
//	@Bean
//	CommandLineRunner commandLineRunner(
//			UserRepository userRepository,
//			TodoListRepository todoListRepository
//	) {
//		return args -> {
//			User user = new User();
//			user.setId(1L);
//			user.setUsername("admin");
//			user.setEmail("admin@admin.com");
//			user.setPassword("admin");
//			user.setTodoLists(user, "My First Todo", "This is a description for my first todo.", "PENDING");
//
//			Chat chat = new Chat();
//			chat.setId(1L);
//			chat.setSender(user);
//			chat.setContent("123");
////			chat.setTodoList();
//
////			TodoList todoList = new TodoList();
////			todoList.setId(1L);
////			todoList.setTitle("My First Todo");
////			todoList.setDescription("This is a description for my first todo.");
////			todoList.setStatus("PENDING");
////
////			user.setTodoLists(Arrays.asList(todoList));
////			todoList.setUser(user);
////			todoListRepository.save(todoList);
//			userRepository.save(user);
//		};
//	}

}
