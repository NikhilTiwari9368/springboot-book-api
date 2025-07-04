package com.bootapi.restapi.dao;

import com.bootapi.restapi.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    // JpaRepository already provides findById, save, deleteById, etc.
    public Book findById(int id);
}