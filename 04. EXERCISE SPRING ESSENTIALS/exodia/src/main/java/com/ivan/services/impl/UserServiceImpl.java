package com.ivan.services.impl;

import com.ivan.entities.User;
import com.ivan.exceptions.InvalidRegisterUserBindingModelException;
import com.ivan.models.service.UserLoginServiceModel;
import com.ivan.models.service.UserRegisterServiceModel;
import com.ivan.models.service.UserServiceModel;
import com.ivan.repositories.UserRepository;
import com.ivan.services.api.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    private static final String INVALID_BINDING_MODEL = "Invalid Binding Model";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserServiceModel> getAll() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel save(UserRegisterServiceModel userRegisterServiceModel) {
        if (!isUsernameFree(userRegisterServiceModel.getUsername()) || !isEmailFree(userRegisterServiceModel.getEmail())) {
            throw new InvalidRegisterUserBindingModelException(INVALID_BINDING_MODEL);
        }
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        if (!userRegisterServiceModel.getPassword().equals(userRegisterServiceModel.getConfirmPassword())) {
            throw new InvalidRegisterUserBindingModelException(INVALID_BINDING_MODEL);
        }
        user.setPassword(DigestUtils.sha256Hex(userRegisterServiceModel.getPassword()));

        return modelMapper.map(userRepository
                .save(user), UserServiceModel.class);
    }

    @Override
    public boolean isUserAvailable(UserLoginServiceModel userLoginServiceModel) {
        return userRepository.findByUsernameAndPassword(userLoginServiceModel.getUsername(),
                DigestUtils.sha256Hex(userLoginServiceModel.getPassword())) != null;
    }

    private boolean isUsernameFree(String username) {
        return userRepository.countAllByUsername(username) == 0;
    }

    private boolean isEmailFree(String email) {
        return userRepository.countAllByEmail(email) == 0;
    }
}
