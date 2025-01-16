package com.example.sboot.service;

import com.example.sboot.entity.Book;
import com.example.sboot.entity.Customer;
import com.example.sboot.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Transactional
    public Book register(Book book){
        return bookRepository.save(book); // save() -> insert SQL
    }
}
