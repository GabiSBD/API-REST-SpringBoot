package com.example.Laptop.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;



import java.util.HashMap;

import java.util.Map;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
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

        ArrayList<UserDetails> list= new ArrayList<>();

        for(com.example.Laptop.entity.User registro : listRepository){
            if(registro.getUsername()=="admin"){
                UserDetails admin = User.builder()
                        .username(registro.getUsername())
                        .password(passwordEncoder().encode(registro.getPassword()))
                        .roles("ADMIN", "USER")
                        .build();
                list.add(admin);

            }
            else {
                UserDetails user = User.builder()
                        .username(registro.getUsername())
                        .password(passwordEncoder().encode(registro.getPassword()))
                        .roles("USER")
                        .build();
                list.add(user);

            }
        }
        return new InMemoryUserDetailsManager(list);
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