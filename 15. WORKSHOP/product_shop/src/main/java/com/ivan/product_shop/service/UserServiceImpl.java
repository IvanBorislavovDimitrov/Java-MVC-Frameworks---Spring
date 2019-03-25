package com.ivan.product_shop.service;

import com.ivan.product_shop.model.binding.UserBindingModel;
import com.ivan.product_shop.model.binding.UserViewModel;
import com.ivan.product_shop.model.entity.User;
import com.ivan.product_shop.repository.RoleRepository;
import com.ivan.product_shop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(value = "userDetailsServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(UserBindingModel userBindingModel) {
        User user = modelMapper.map(userBindingModel, User.class);

        if (userRepository.count() == 0) {
            user.getRoles().add(roleRepository.findByName("ADMIN"));
            user.getRoles().add(roleRepository.findByName("MODERATOR"));
            user.getRoles().add(roleRepository.findByName("ROOT"));
            user.getRoles().add(roleRepository.findByName("USER"));
        } else {
            user.getRoles().add(roleRepository.findByName("USER"));
        }

        user.setPassword(bCryptPasswordEncoder.encode(userBindingModel.getPassword()));

        userRepository.save(user);
    }

    @Override
    public UserViewModel findByUsername(String name) {
        return modelMapper.map(userRepository.findByUsername(name), UserViewModel.class);
    }

    @Override
    public void update(UserViewModel userViewModel) {
        User user = userRepository.findByUsername(userViewModel.getUsername());
        if (!bCryptPasswordEncoder.matches(userViewModel.getOldPassword(), user.getPassword())) {
            throw new IllegalStateException();
        }

        user.setPassword(bCryptPasswordEncoder.encode(userViewModel.getPassword()));
        user.setEmail(userViewModel.getEmail());

        userRepository.saveAndFlush(user);
    }

    @Override
    public List<UserViewModel> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().noneMatch(u -> u.getName().equals("ROOT")))
                .map(user -> {
                    UserViewModel userViewModel = modelMapper.map(user, UserViewModel.class);
                    userViewModel.getRoles().clear();
                    user.getRoles().forEach(role -> userViewModel.getRoles().add(role.getName()));
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void addAdmin(String id) {
        User user = userRepository.findById(id).orElse(null);

        user.getRoles().add(roleRepository.findByName("ADMIN"));

        userRepository.saveAndFlush(user);
    }

    @Override
    public void addModerator(String id) {
        User user = userRepository.findById(id).orElse(null);

        user.getRoles().add(roleRepository.findByName("MODERATOR"));

        userRepository.saveAndFlush(user);
    }

    @Override
    public void addUser(String id) {
        User user = userRepository.findById(id).orElse(null);

        user.getRoles().add(roleRepository.findByName("USER"));

        userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
