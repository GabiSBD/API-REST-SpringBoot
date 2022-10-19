package com.example.Laptop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Este controler tiene el proposito de testear la comunicacion desde el exterior de manera facil y
 * mostrar desde que perfil se esta ejecutando a traves de la consola del sistema con un mensaje declarado en la
 * hoja de properties del perfil en cuestion
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        System.out.println(message);
        return "hello world!!";
    }
    @Value("${app.message}")
    private String message;
}
