package com.example.bookstore.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.entitiy.Book;
import com.example.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String list(Model model) {
        List<BookDto> bookList = bookService.getList();
        model.addAttribute("list",bookList);
        return "/book/list";
    }

    // @GetMapping("/{idx}")
    // public String detail(@PathVariable("idx") Long idx, Model model) {
    //     log.info("detail");
    //     Optional<Book> book = bookService.get(idx);
    //     if(book.isPresent()){
    //         BookDto bookDto = new BookDto();
    //         bookDto.toEntity();
    //         model.addAttribute("book", book.get().builder());
    //         return "book/detail";
    //     }else {
    //         return "redirect:/book";
    //     }
    // }

    
    @GetMapping("/{idx}")
    public String detail(@PathVariable("idx") Long idx, Model model) {
        log.info("detail");
        BookDto bookDto = bookService.get(idx);
        bookDto.toEntity();
        model.addAttribute("bookDto", bookDto);

        return "book/detail";
    }
    
    @GetMapping("/write")
    public String write() {
        return "/book/write";
    }

    @PostMapping("/create")
    public String execSignup(BookDto bookDto) {
        log.info("bookDto :: " +bookDto);
        bookService.create(bookDto);

        return "redirect:/book";
    }

    @GetMapping("/edit/{idx}")
    public String edit(Long idx) {
        
        return "redirect:/book/edit";

    }

    @PutMapping("/{idx}")
    public String update(BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/book";

    }

    @DeleteMapping("/{no}")
    public String delete(@PathVariable("no") Long no) {
        return "/book";

    }
}