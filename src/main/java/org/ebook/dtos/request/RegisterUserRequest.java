package org.ebook.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
}
