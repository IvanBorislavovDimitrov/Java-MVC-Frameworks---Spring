package com.ivan.resident_evil.service;

import com.ivan.resident_evil.model.dto.binding.UserRegisterBindingModel;
import com.ivan.resident_evil.model.dto.service.UserRegisterServiceModel;

import java.util.List;

public interface UserService {

    void save(UserRegisterServiceModel userRegisterServiceModel);

    boolean hasRoleAdmin(String currentUserName);

    boolean hasRoleModerator(String currentUserName);

    List<UserRegisterServiceModel> findAll();

    void addAdminRole(String username);

    void addModerator(String username);

    void removeAdmin(String username);

    void removeModerator(String username);
}
