package de.dhbwka.tippspiel.repositories;

import de.dhbwka.tippspiel.entities.ERole;
import de.dhbwka.tippspiel.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(@Param("name") ERole name);
}

