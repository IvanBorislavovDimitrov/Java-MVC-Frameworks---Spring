package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.entities.Role;
import com.ivan.resident_evil.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            save("ADMIN");
            save("MODERATOR");
            save("ROOT");
            save("USER");
        }
    }

    @Override
    public void save(String role) {
        Role r = new Role();
        r.setName(role);
        roleRepository.save(r);
    }

    public Role getRole(String role) {
        return roleRepository.findByName(role);
    }
}
