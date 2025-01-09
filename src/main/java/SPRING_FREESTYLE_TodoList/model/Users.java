package SPRING_FREESTYLE_TodoList.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "username_unique", columnNames = "username"),
                @UniqueConstraint(name = "email_unique", columnNames = "email")
        }
)
public class Users {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TodoList> todoLists = new ArrayList<>();

//    buat startup doang
    public void setTodoLists(Users user, String title, String description, String status){
        TodoList todoList = new TodoList();
        todoList.setCreated_at(LocalDateTime.now());
        todoList.setUpdated_at(LocalDateTime.now());
        todoList.setExcecute_at(LocalDateTime.now());
        todoList.setUser(user);
        todoList.setTitle(title);
        todoList.setDescription(description);
        todoList.setStatus(status);
        todoLists.add(todoList);
    }
}
