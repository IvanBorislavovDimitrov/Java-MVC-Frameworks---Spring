package com.ivan.services.api;

import com.ivan.models.service.UserLoginServiceModel;
import com.ivan.models.service.UserRegisterServiceModel;
import com.ivan.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    List<UserServiceModel> getAll();

    UserServiceModel save(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUserAvailable(UserLoginServiceModel userLoginServiceModel);
}
