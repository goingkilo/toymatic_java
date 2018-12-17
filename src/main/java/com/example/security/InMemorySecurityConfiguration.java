package com.example.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

//@Configuration
//@EnableGlobalAuthentication
public class InMemorySecurityConfiguration {

//    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("password")
                .roles("USER", "ADMIN");
    }
}