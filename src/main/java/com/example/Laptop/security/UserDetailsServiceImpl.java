package com.example.Laptop.security;

import com.example.Laptop.entity.User;
import com.example.Laptop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> list = userRepository.findAll();
        User busqueda = null;
        for(User usuario : list){
           if( usuario.getUsername()==username) busqueda=usuario;
        }
        if(busqueda==null)throw new UsernameNotFoundException("User Not Found with username: " + username);


        return new org.springframework.security.core.userdetails.User(
                busqueda.getUsername(),busqueda.getPassword(),new ArrayList<>());
    }
}

