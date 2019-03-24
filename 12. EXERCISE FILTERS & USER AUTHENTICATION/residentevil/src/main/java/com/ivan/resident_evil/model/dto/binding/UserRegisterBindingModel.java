package com.ivan.resident_evil.model.dto.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {

    private static final String INVALID_USERNAME = "Invalid username";
    private static final String INVALID_PASSWORD = "Invalid password";
    private static final String INVALID_EMAIL = "Invalid email";

    @NotEmpty(message = INVALID_USERNAME)
    private String username;

    @NotEmpty(message = INVALID_PASSWORD)
    private String password;

    @NotEmpty(message = INVALID_PASSWORD)
    private String confirmPassword;

    @Pattern(regexp = "^[A-Za-z][A-Za-z.0-9]+@([A-Za-z]+(\\.)){1,}[A-Za-z0-9]+$", message = INVALID_EMAIL)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
