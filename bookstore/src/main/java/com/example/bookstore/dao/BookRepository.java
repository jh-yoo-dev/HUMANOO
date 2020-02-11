package com.example.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstore.model.entitiy.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}