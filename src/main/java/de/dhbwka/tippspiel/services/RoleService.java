package de.dhbwka.tippspiel.services;

import de.dhbwka.tippspiel.entities.ERole;
import de.dhbwka.tippspiel.entities.Role;
import de.dhbwka.tippspiel.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createDefaultRoles() {
        Role roleUser = new Role();
        roleUser.setName(ERole.ROLE_USER);
        roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName(ERole.ROLE_ADMIN);
        roleRepository.save(roleAdmin);
    }
}
