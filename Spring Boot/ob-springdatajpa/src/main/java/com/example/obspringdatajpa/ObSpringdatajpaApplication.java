package com.example.obspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringdatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObSpringdatajpaApplication.class, args);
		CocheRepository repository = context.getBean(CocheRepository.class);

		System.out.println("find");
		System.out.println(repository.count());

		Coche coche = new Coche(null, "Peugeot", "208", 2010);

		repository.save(coche);
		System.out.println("El numero de coche en base de datos es: " + repository.count());
		System.out.println(repository.findAll());
	}

}
