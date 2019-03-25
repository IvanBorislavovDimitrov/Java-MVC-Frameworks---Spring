package com.ivan.product_shop.model.binding;

import javax.validation.constraints.NotEmpty;

public class UserBindingModel {

    @NotEmpty(message = "invalid username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "invalid email")
    private String email;

    @NotEmpty(message = "invalid passsowrd")
    private String password;

    @NotEmpty(message = "invalid confirm password")
    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
