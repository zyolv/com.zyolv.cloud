package com.zyolv.cloud.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


@Configuration
public class TokenConfig {

    private static final String SIGNING_KEY = "uaa";


    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        //数据库方式存储token
        //return new JdbcTokenStore(dataSource);

        //redis方式存储token
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);

        return redisTokenStore;


        //return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}