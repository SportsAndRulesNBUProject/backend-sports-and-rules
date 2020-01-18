package com.nbu.sportsandrules.service;

import com.nbu.sportsandrules.entity.ERole;
import com.nbu.sportsandrules.entity.Role;
import com.nbu.sportsandrules.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SeedDataService {

    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void loadRolesOnStartUp(ApplicationStartedEvent event) {
        ERole[] roles = ERole.values();

        for (ERole eRole : roles) {
            if (!roleRepository.existsByName(eRole)) {
                Role role = new Role();
                role.setName(eRole);

                roleRepository.save(role);
            }
        }
    }
}
