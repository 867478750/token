package com.nlb.auth2.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        super.configure(auth);
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser(user1).withUser(user2);
//        super.configure(auth);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = new User("nlb","123", AuthorityUtils.createAuthorityList("root"));
//        UserDetails user2 = new User("root","root", AuthorityUtils.createAuthorityList("root"));
//        InMemoryUserDetailsManager detailsManager = new InMemoryUserDetailsManager();
//        detailsManager.createUser(user1);
//        detailsManager.createUser(user2);
//        return detailsManager;
//    }
}
