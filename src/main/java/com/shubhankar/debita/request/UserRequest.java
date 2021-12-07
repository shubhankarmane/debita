package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@ToString
public class UserRequest {
    @NotNull
    @Size(min = 1, max = 150)
    private String firstName;
    @Size(max = 150)
    private String lastName;
    @NotNull
    @Size(max = 150)
    private String email;
    @NotNull
    @Size(min = 6, max = 14)
    private String password;
}
