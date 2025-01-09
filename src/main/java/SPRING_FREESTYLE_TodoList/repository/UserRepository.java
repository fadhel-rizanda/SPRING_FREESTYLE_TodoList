package SPRING_FREESTYLE_TodoList.repository;

import SPRING_FREESTYLE_TodoList.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Page<Users> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
    Optional<Users> findByUsername(String username);
}
