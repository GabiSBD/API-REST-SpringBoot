package com.example.Laptop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 *Este controller sirve para crear una direcion url base y una pagina inicial cargando la pagina a partir de un documento html
 * ademas muestra por cosola de la aplicacion desde que perfil se ejecuta la aplicacion ( el texto se guarda en la hoja de
 * properties pertinente.
 */
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello(){
        while(cont<1){
        System.out.println(message);
        cont++;
        }

        String html="";
        try {
             FileInputStream file = new FileInputStream("pagina inicial laptops.html");

            byte[]buffer= file.readAllBytes();
            html= new String(buffer, StandardCharsets.UTF_8);
            file.close();

        } catch (IOException e) {
            log.error("HTML document not found");
        }
        return html;
    }
    @Value("${app.message}")
    private String message;
    private int cont=0;
    private final Logger log = LoggerFactory.getLogger(HelloController.class);
}
