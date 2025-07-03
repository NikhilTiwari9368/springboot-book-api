package com.bootapi.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootapi.restapi.dao.BookRepository;
import com.bootapi.restapi.entities.Book;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // Get a book by ID
    public Book getBookById(int id) {
    return this.bookRepository.findById(id);
    }
    // Add a new book
    public Book addBook(Book book) {
        Book result = this.bookRepository.save(book);
        return result;
    }

    // Delete a book by ID
    public void deleteBook(int bookId) {
        this.bookRepository.deleteById(bookId);
    }

    // Update a book by ID
    public Book updateBook(Book book, int bookId) {
        if (this.bookRepository.existsById(bookId)) {
            book.setId(bookId); // Set ID to ensure update
            return this.bookRepository.save(book);
        } else {
            return null; // Optional: throw new ResourceNotFoundException("Book not found");
        }
    }
}
