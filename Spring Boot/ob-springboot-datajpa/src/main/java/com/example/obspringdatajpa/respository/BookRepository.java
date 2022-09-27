package com.example.obspringdatajpa.respository;

import com.example.obspringdatajpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
