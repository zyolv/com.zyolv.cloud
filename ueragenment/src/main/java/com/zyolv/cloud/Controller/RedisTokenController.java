package com.zyolv.cloud.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/redis")
public class RedisTokenController {
    @Autowired
    private TokenStore redisTokenStore;
    @GetMapping("/find")
    public Object find( String clientId){
        return redisTokenStore.findTokensByClientId(clientId);
    }
    @GetMapping("/del")
    public Object del( String clientId){
        Collection<OAuth2AccessToken> tokens = redisTokenStore.findTokensByClientId(clientId);
        tokens.forEach(token-> redisTokenStore.removeAccessToken(token));
        return "删除成功";
    }

}
