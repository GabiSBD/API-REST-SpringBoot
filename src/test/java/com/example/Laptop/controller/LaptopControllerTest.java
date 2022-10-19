package com.example.Laptop.controller;

import com.example.Laptop.LaptopApplication;
import com.example.Laptop.entity.Laptop;
import com.example.Laptop.repository.LaptopRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    @BeforeEach
    void setUp() {
        templateBuilder = new RestTemplateBuilder().rootUri("http://localhost:"+port);
         template = new TestRestTemplate(templateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                template.getForEntity("/api/laptops",Laptop[].class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response =
                template.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(401,response.getStatusCodeValue());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "manufacturer": "HP",
                    "author": "RX-300"
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response =
                template.exchange("/api/laptops",HttpMethod.POST,request, Laptop.class );

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertNotEquals("", result.getManufacturer());
        assertNotEquals("", result.getModel());
        assertEquals(HttpStatus.OK, response.getStatusCode());


    }
    @LocalServerPort
    private int port;
    private TestRestTemplate template;
    @Autowired
    private RestTemplateBuilder templateBuilder;
}