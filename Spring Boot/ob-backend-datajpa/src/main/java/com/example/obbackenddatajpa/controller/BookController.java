package com.example.obbackenddatajpa.controller;

import com.example.obbackenddatajpa.entities.Book;
import com.example.obbackenddatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;
    public BookController(BookRepository repository){
        this.bookRepository = repository;
    }

    //CRUD sobre la entidad Book

    /**
     * Buscar todos los libros que hay en base de datos(ArrayList de libros)
     * http://localhost:8080/api/books
     * @return
     **/
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    /**
     * Buscar un solo libro por ID
     * http://localhost:8080/api/books/1
     * http://localhost:8080/api/books/2
     * Request
     * Response
     * http://localhost:8080/api/books
     * @param id
     * @return
     **/
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        //Option 1
        Optional<Book> bookOpt =  bookRepository.findById(id);
        if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();
        //option 2
        //return bookOpt.orElse(null);
            //return bookOpt.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
        //}
    }

    /**
     * Crear un libro recibido desde parametro en la base de datos
     * Metodo Post, no colisiona con findAll porque son diferentes metodos HTTP: GET vs POST
     *
     * @param book
     * @param headers
     * @return
     */
    @PostMapping("api/books")
    public ResponseEntity<Object> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro recibido por paramentro en la base de datos
        if(book.getId() != null){ //quiere decir que existe el id y por lo tanto no es una creacion
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); //el libro devuelto tiene una clave primaria
    }

    /**
     * Actualizar un libro existente en base de datos
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null){ //si no tiene ID quiere decir que si es una creación
            log.warn("Trying to update a non existent book");
            ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        //El proceso de actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); //el libro devuelto tiene una clave primaria
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("api/books")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST Request Deleting all books");
        if(bookRepository.count()== 0){
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
