package de.dhbwka.tippspiel.repositories;
import de.dhbwka.tippspiel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.benutzername = :username")
    User getUserByUsername(@Param("username") String username);

}
