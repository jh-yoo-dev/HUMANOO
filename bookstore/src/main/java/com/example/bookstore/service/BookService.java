package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.entitiy.Book;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    @Transactional
    public Long create(BookDto bookDto) {
        return bookRepository.save(bookDto.toEntity()).getIdx();
    }

    @Transactional
    public BookDto get(Long idx) {
        BookDto bookDto = new BookDto();
        Optional<Book> result = bookRepository.findById(idx);
        if(result.isPresent()){
            Book book = result.get();
            bookDto = BookDto.builder()
                .idx(book.getIdx())
                .id(book.getId())
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .author(book.getAuthor())
                .categories(book.getCategories())
                .createdDate(book.getCreatedDate())
                .modifiedDate(book.getModifiedDate())
                .build();
        }
        return bookDto;
    }

    @Transactional
    public Long update(BookDto bookDto){
        return bookRepository.save(bookDto.toEntity()).getIdx();
    }

    public List<BookDto> getList() {
        log.info("getList called");
        List<Book> bookList = bookRepository.findAll();
        log.info("bookList :: " + bookList);

        List<BookDto> bookDtoList = new ArrayList<>();

        for(Book book : bookList){
            BookDto bookDto = BookDto.builder()
                .idx(book.getIdx())
                .id(book.getId())
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .author(book.getAuthor())
                .categories(book.getCategories())
                .createdDate(book.getCreatedDate())
                .modifiedDate(book.getModifiedDate())
                .build();

            bookDtoList.add(bookDto);
        }

        return bookDtoList;

    }

}