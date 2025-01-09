package SPRING_FREESTYLE_TodoList.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static jakarta.persistence.GenerationType.SEQUENCE;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TodoList")
@Table(name = "todoList")
public class TodoList {

    @Id
    @SequenceGenerator(
        name = "todo_list_sequence",
        sequenceName = "todo_list_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "todo_list_sequence"
    )

    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Users user;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, nullable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime updated_at;

    @Column(name = "excecute_at")
    private LocalDateTime excecute_at;

//    "PENDING", "IN_PROGRESS", "COMPLETED"
    @Column(name = "status", nullable = false)
    private String status;
}
