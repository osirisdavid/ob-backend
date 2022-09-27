package com.example.obspringdatajpa.controller;

import com.example.obspringdatajpa.entities.Book;
import com.example.obspringdatajpa.respository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    BookRepository bookRepository;
    public BookController(BookRepository repository){
        this.bookRepository = repository;
    }

    //CRUD sobre la entidad Book
    /**
     * http://localhost:8080/api/books
     * @return
     **/

    //Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    //Buscar un solo libro por ID
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        //Opcion 1
        Optional<Book> bookOpt =  bookRepository.findById(id);
        if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    //Crear un libro recibido desde parametro en la base de datos
    @PostMapping("api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        return bookRepository.save(book);
    }
}
