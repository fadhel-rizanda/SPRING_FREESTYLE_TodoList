package SPRING_FREESTYLE_TodoList.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Chat")
@Table(name = "chats")
public class Chat {
    @Id
    @SequenceGenerator(
            name = "chat_sequence",
            sequenceName = "chat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "chat_sequence"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
//    @JsonBackReference
    private Users sender;

    @Column(name = "content", updatable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "todo_list_id")
//    @JsonBackReference
    private TodoList todoList;

    @Column(name = "send_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime send_at;
}
