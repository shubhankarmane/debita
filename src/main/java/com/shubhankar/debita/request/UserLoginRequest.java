package com.shubhankar.debita.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginRequest {
    @NotNull
    @Size(max = 150)
    private String email;
    @NotNull
    @Size(min = 6, max = 14)
    private String password;

    public UserLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

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
}
