package com.era.authserver.model.requests;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}
