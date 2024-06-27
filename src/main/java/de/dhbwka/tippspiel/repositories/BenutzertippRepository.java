package de.dhbwka.tippspiel.repositories;

import de.dhbwka.tippspiel.entities.Benutzertipp;
import de.dhbwka.tippspiel.entities.ERole;
import de.dhbwka.tippspiel.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BenutzertippRepository extends JpaRepository<Benutzertipp, Long> {

    @Query("SELECT bt FROM Benutzertipp bt WHERE bt.username = :username")
    Optional<Benutzertipp> findByUsername(@Param("username") String username);

    @Query("SELECT bt FROM Benutzertipp bt WHERE bt.username = :username AND bt.matchID = :matchID")
    Benutzertipp findByUsernameAndMatchID(@Param("username") String username, @Param("matchID") Long matchID);
}
