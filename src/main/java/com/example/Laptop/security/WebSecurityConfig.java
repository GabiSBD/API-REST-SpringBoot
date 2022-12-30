package com.example.Laptop.security;


import com.example.Laptop.entity.User;
import com.example.Laptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

   //private UserRepository userRepository;
   @Autowired
  private UserDetailsServiceImpl userDetailsService;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/");
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
   /* @Bean
    public UserDetailsService userDetailsService() {

         List<User> userList = userRepository.findAll();
        ArrayList<UserDetails>detailsList =new ArrayList ();
        for(User usu : userList){
            UserDetails user = org.springframework.security.core.userdetails.User.builder().
                    username(usu.getUsername()).password(usu.getPassword()).build();
            detailsList.add(user);
        }
        return new InMemoryUserDetailsManager(detailsList);
    }*/

    @Bean
    public static PasswordEncoder passwordEncoder(){

        String idForEncoder = "Pbkdf2";

        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        encoderMap.put("Bcrypt", new BCryptPasswordEncoder());
        encoderMap.put("Pbkdf2", new Pbkdf2PasswordEncoder());
        encoderMap.put("Argon2", new Argon2PasswordEncoder());


        return new DelegatingPasswordEncoder(idForEncoder, encoderMap);
    }

}