package com.example.obbackenddatajpa;

import com.example.obbackenddatajpa.entities.Book;
import com.example.obbackenddatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObBackendDatajpaApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ObBackendDatajpaApplication.class, args);
		ApplicationContext context = SpringApplication.run(ObBackendDatajpaApplication.class, args); //Nos da el contenedor de Beans
		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD
		//crear un libro
		Book book1 = new Book(null, "Homo Deus", "Yuval Noah", 450, 29.99, LocalDate.of(2022,9,9), true);
		Book book2 = new Book(null, "Homo Sapiens", "Yuval Noah", 450, 29.99, LocalDate.of(2013,9,9), true);

		//almacenar un libro
		repository.save(book1);
		repository.save(book2);

		//recuperar un libre
		System.out.println(repository.findAll());

		//borrar un libro
		//repository.deleteById(1L);
	}

}
