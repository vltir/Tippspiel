package de.dhbwka.tippspiel.repositories;

import de.dhbwka.tippspiel.entities.Benutzerpunkte;
import de.dhbwka.tippspiel.entities.Benutzertipp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BenutzerpunkteRepository extends JpaRepository<Benutzerpunkte, String>  {

    @Query("SELECT bp FROM Benutzerpunkte bp WHERE bp.username = :username")
    Benutzerpunkte findByUsername(@Param("username") String username);

}
