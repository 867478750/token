package com.nlb.auth2.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Override//配置客户端
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//将token存储在内存中
        .withClient("web")//token平台的名称
        .secret("root")//平台的密码
        .authorizedGrantTypes("password")//使用用户名密码登录来访问token
        .scopes("web")
        .and()
        .inMemory()
        .withClient("app")
        .secret("app")
        .authorizedGrantTypes("password")//authorized_code授权码模式，根据授权码来获取token
        .scopes("app");
        super.configure(clients);
    }

    @Override//配置token怎么存储
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore());
        super.configure(endpoints);
    }

    @Override//配置token的访问权限
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");//允许所有人访问
        super.configure(security);
    }


    private TokenStore tokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }
}
