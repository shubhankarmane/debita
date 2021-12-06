package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@ToString
public class UserRequest {
    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
