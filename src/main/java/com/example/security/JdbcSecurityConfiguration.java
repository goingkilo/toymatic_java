package com.example.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.ResultSet;

//@Configuration
//@EnableGlobalAuthentication
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter{

    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
        RowMapper<User> userRowMapper = (ResultSet rs, int i) ->
                new User(
                        rs.getString("ACCOUNT_NAME"),
                        rs.getString("PASSWORD"),
                        rs.getBoolean("ENABLED"),
                        rs.getBoolean("ENABLED"),
                        rs.getBoolean("ENABLED"),
                        rs.getBoolean("ENABLED"),
                        AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
        return username ->
                jdbcTemplate.queryForObject("SELECT * from ACCOUNT where ACCOUNT_NAME = ?",
                        userRowMapper, username);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }
}

/*

         ** THIS NEEDS FLYAWAY **
         * https://www.baeldung.com/database-migrations-with-flyway
         *  =

        -- SECURITY: USER ACCOUNT
        DROP TABLE IF EXISTS account;
        CREATE TABLE account ( ACCOUNT_NAME VARCHAR(255) NOT NULL,
        PASSWORD VARCHAR(255 ) NOT NULL,
        ID SERIAL,
        ENABLED BOOL DEFAULT true) ;

        -- JOURNAL TABLE: ENTRY
        DROP TABLE IF EXISTS entry;
        CREATE TABLE entry (
        ID BIGINT(20) NOT NULL AUTO_INCREMENT,
        CREATED DATETIME DEFAULT NULL,
        SUMMARY VARCHAR(255) DEFAULT NULL,
        TITLE VARCHAR(255) DEFAULT NULL,
        PRIMARY KEY (ID)
        );
        Listing 9-5 shows the schema.sql, which contains the mandatory account table for the security. This table is mandatory and is an adaptation from the Spring Security documents: https://docs.spring.io/spring-security/site/docs/current/reference/html/appendix-schema.html . Also notice that the journal table entry is defined, because when you create schema.sql the property (spring.jpa.hibernate.ddl-auto = create-drop) from the application.properties file will drop the table recently created. That means that you need to comment out the property spring.jpa.hibernate.ddl-auto=create-drop (from the application.properties) so it won’t affect the behavior. If you leave this property in, you’ll get something like: "Can't find journal.entry table error".

        Listing 9-6. src/main/resources/data.sql
        -- USERS IN JOURNAL
        INSERT INTO ACCOUNT(account_name , password) VALUES('springboot', 'isawesome');
        INSERT INTO ACCOUNT(account_name , password) VALUES('springsecurity', 'isawesometoo');

        -- JOURNAL DATA
        INSERT INTO ENTRY(title,summary,created) VALUES('Get to know Spring Boot','Today I will learn Spring Boot','2016-01-02 00:00:00.00');
        INSERT INTO ENTRY(title,summary,created) VALUES('Simple Spring Boot Project','I will do my first Spring Boot project','2016-01-03 00:00:00.00');
        INSERT INTO ENTRY(title,summary,created) VALUES('Spring Boot Reading','Read more about Spring Boot','2016-02-02 00:00:00.00');
        INSERT INTO ENTRY(title,summary,created) VALUES('Spring Boot in the Cloud','Learn Spring Boot using Cloud Foundry','2016-02-05 00:00:00.00');
        Listing 9-6 shows data.sql. You will add the two account users and the journal data to this file. Now you are ready to run the journal app. Remember before you run it to comment out the spring.jpa.hibernate.ddl-auto = create-drop property from the application.properties.
*/