package SPRING_FREESTYLE_TodoList.repository;

import SPRING_FREESTYLE_TodoList.model.TodoList;
import SPRING_FREESTYLE_TodoList.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    Page<TodoList> findByUser(Users user, Pageable pageable);

    Page<TodoList> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
