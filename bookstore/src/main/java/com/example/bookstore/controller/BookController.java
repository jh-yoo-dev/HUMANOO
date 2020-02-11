package com.example.bookstore.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.entitiy.Book;
import com.example.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @GetMapping
    public String list() {
        return "/book/list";
    }

    @GetMapping("/write")
    public String write() {
        return "/book/write";
    }

    @PostMapping("/create")
    public String execSignup(BookDto bookDto) {
        System.out.println("bookDto :: " +bookDto);
        bookService.create(bookDto);

        return "redirect:/book";
    }

    @PutMapping
    public String update(Book book) {
        return "/book";

    }

  
}