package com.shubhankar.debita.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
