package com.example.Laptop.security;


import com.example.Laptop.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    private UserRepository userRepository;
   private List<com.example.Laptop.entity.User> listRepository = userRepository.findAll();
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/");
    }


    @Bean
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
    }

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