package de.dhbwka.tippspiel.repositories;

import de.dhbwka.tippspiel.entities.Benutzerpunkte;
import de.dhbwka.tippspiel.entities.Benutzertipp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BenutzerpunkteRepository extends JpaRepository<Benutzerpunkte, String>  {

    @Query("SELECT bp FROM Benutzerpunkte bp WHERE bp.username = :username")
    Optional<Benutzerpunkte> findByUsername(@Param("username") String username);

}
