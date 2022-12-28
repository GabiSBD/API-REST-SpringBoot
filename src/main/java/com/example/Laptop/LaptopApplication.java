package com.example.Laptop;

import com.example.Laptop.entity.Laptop;
import com.example.Laptop.entity.User;
import com.example.Laptop.repository.LaptopRepository;
import com.example.Laptop.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LaptopApplication.class, args);

		/*UserRepository repository = context.getBean(UserRepository.class);

		//Añadimos a la bbdd H2 dos obj.Laptop a traves de laptop repository
		*//*repository.save(new Laptop(null, "toshiba","gaming"));
		repository.save(new Laptop(null, "IBM", "magestic-RG"));*//*
		repository.save(new User("admin","admin@gmail.ad","AdminRules"));
		repository.save(new User("cristi","cristiguapi@gmail.user","pepi"));*/
	}

}
