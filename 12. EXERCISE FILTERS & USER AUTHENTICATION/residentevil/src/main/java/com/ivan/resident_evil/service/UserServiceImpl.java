package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.dto.service.UserRegisterServiceModel;
import com.ivan.resident_evil.model.entities.User;
import com.ivan.resident_evil.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "userDetailsServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleServiceImpl roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void save(UserRegisterServiceModel userRegisterServiceModel) {
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (userRepository.count() == 0) {
            user.setRoles(new HashSet<>(Collections.singletonList(roleService.getRole("ROOT"))));
        } else {
            user.setRoles(new HashSet<>(Collections.singletonList(roleService.getRole("USER"))));
        }
        userRepository.save(user);
    }

    @Override
    public boolean hasRoleAdmin(String currentUserName) {
        return userRepository.findByUsername(currentUserName).getAuthorities()
                .stream()
                .anyMatch(role -> role.getAuthority().contains("ADMIN") || role.getAuthority().contains("ROOT"));
    }

    @Override
    public boolean hasRoleModerator(String currentUserName) {
        return userRepository.findByUsername(currentUserName).getAuthorities()
                .stream()
                .anyMatch(role -> role.getAuthority().contains("MODERATOR"));
    }

    @Override
    public List<UserRegisterServiceModel> findAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserRegisterServiceModel model = modelMapper.map(user, UserRegisterServiceModel.class);
                    model.getRoles().clear();
                    user.getAuthorities().forEach(a -> model.getRoles().add(a.getAuthority()));
                    return model;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addAdminRole(String username) {
        User user = userRepository.findByUsername(username);
        user.getRoles().add(roleService.getRole("ADMIN"));

        userRepository.save(user);
    }

    @Override
    public void addModerator(String username) {
        User user = userRepository.findByUsername(username);
        user.getRoles().add(roleService.getRole("MODERATOR"));

        userRepository.save(user);

    }

    @Override
    public void removeAdmin(String username) {
        User user = userRepository.findByUsername(username);
        user.getRoles().remove(roleService.getRole("ADMIN"));

        userRepository.save(user);

    }

    @Override
    public void removeModerator(String username) {
        User user = userRepository.findByUsername(username);
        user.getRoles().remove(roleService.getRole("MODERATOR"));

        userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
