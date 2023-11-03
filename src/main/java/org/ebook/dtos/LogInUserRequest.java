package org.ebook.dtos;

import lombok.Data;

@Data
public class LogInUserRequest {
    private String username;
    private String password;
}
