package de.dhbwka.tippspiel.repositories;
import de.dhbwka.tippspiel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
