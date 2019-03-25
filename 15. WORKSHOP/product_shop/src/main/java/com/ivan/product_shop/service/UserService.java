package com.ivan.product_shop.service;

import com.ivan.product_shop.model.binding.UserBindingModel;
import com.ivan.product_shop.model.binding.UserViewModel;

import java.util.List;

public interface UserService {

    void save(UserBindingModel userBindingModel);

    UserViewModel findByUsername(String name);

    void update(UserViewModel userViewModel);

    List<UserViewModel> getAllUsers();

    void addAdmin(String id);

    void addModerator(String id);

    void addUser(String id);
}
