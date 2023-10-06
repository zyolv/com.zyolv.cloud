package com.zyolv.cloud.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenReq {
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
    private String grantType;

}
