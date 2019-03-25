package com.ivan.product_shop.service;

import com.ivan.product_shop.model.entity.Role;
import com.ivan.product_shop.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ADMIN"));
            roleRepository.save(new Role("MODERATOR"));
            roleRepository.save(new Role("ROOT"));
            roleRepository.save(new Role("USER"));
        }
    }
}
