package com.example.service;

import com.example.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    // Example method to fetch all books
    public List<Book> getAllBooks() {
        // In a real application, you would query the database
        return books;
    }

    // Example method to add a book
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    // Example method to get a book by ID
    public Book getBookById(Long id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }
}
