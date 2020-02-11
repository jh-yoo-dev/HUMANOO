package com.example.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.entitiy.Book;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    @Transactional
    public Long create(BookDto bookDto) {
        return bookRepository.save(bookDto.toEntity()).getIdx();
    }

    public List<Book> getList() {
        return bookRepository.findAll();
    }

}