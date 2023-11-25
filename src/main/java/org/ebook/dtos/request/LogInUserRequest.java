package org.ebook.dtos.request;

import lombok.Data;

@Data
public class LogInUserRequest {
    private String username;
    private String password;
}
