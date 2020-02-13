package com.example.bookstore.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @GetMapping
    public String list(@RequestParam(value="page", defaultValue = "1") Integer pageNum, Model model) {
        List<BookDto> bookList = bookService.getList(pageNum);
        Integer[] pageList = bookService.getPageList(pageNum);
        
        model.addAttribute("list",bookList);
        model.addAttribute("pageList", pageList);
        
        return "/book/list";
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BookDto> bookList = bookService.search(keyword);
        model.addAttribute("list",bookList);
        return "/book/list";
    }

    @GetMapping("/{idx}")
    public String detail(@PathVariable("idx") Long idx, Model model) {
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
        bookService.create(bookDto);

        return "redirect:/book";
    }

    @GetMapping("/edit/{idx}")
    public String edit(@PathVariable("idx") Long idx,Model model) {
        BookDto bookDto = bookService.get(idx);
        bookDto.toEntity();
        model.addAttribute("bookDto", bookDto);

        return "/book/edit";

    }

    @PostMapping("/edit/{idx}")
    public String update(@PathVariable("idx") Long idx, BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/book";

    }

    @PostMapping("/{idx}")
    public String delete(@PathVariable("idx") Long idx) {
        bookService.delete(idx);
        return "redirect:/book";

    }
}