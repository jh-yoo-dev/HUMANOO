package com.example.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.bookstore.dao.BookRepository;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.entitiy.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    private static final int BLOCK_COUNT_PAGE = 5;
    private static final int COUNT_PER_ONE_PAGE = 4;


    @Transactional
    public Long create(BookDto bookDto) {
        return bookRepository.save(bookDto.toEntity()).getIdx();
    }

    @Transactional
    public List<BookDto> search(String keyword){
        List<Book> bookList = bookRepository.findByTitleContaining(keyword);
        List<BookDto> bookDtoList = new ArrayList<>();

        if(bookList.isEmpty()) return bookDtoList;

        for(Book book : bookList){
            bookDtoList.add(convertToDto(book));
        }

        return bookDtoList;
    }

    public BookDto convertToDto(Book book){
        return BookDto.builder()
        .id(book.getId())
        .title(book.getTitle())
        .ISBN(book.getISBN())
        .idx(book.getIdx())
        .author(book.getAuthor())
        .categories(book.getCategories())
        .build();
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

    @Transactional
    public List<BookDto> getList(int pageNum) {
        Page<Book> page = bookRepository.findAll(PageRequest.of(pageNum - 1,COUNT_PER_ONE_PAGE, Sort.by(Sort.Direction.ASC, "createdDate")));

        List<Book> bookList = page.getContent();
        List<BookDto> bookDtoList = new ArrayList<>();

        for(Book book : bookList){
            bookDtoList.add(this.convertToDto(book));
        }

        return bookDtoList;
    }
    
    @Transactional
    public Long getBookCount() {
        return bookRepository.count();
    }

    public Integer[] getPageList(Integer currentPage){
        Integer[] pageList = new Integer[BLOCK_COUNT_PAGE];

        // total count of book list 
        Double totalCount = Double.valueOf(this.getBookCount());
       
        // last page number
        Integer totalLastPage = (int)(Math.ceil(totalCount/COUNT_PER_ONE_PAGE));

        // last page number in block
        Integer lastPage = (totalLastPage > currentPage + BLOCK_COUNT_PAGE)
        ? currentPage + BLOCK_COUNT_PAGE : totalLastPage;
        // start page number
        currentPage = (currentPage <= 3) ? 1 : currentPage - 2;
        
        for(int number = currentPage, idx = 0; number <= lastPage; number++, idx++){
            pageList[idx] = number;
        }

        return pageList;
    }

    @Transactional
    public void delete(Long idx){
        bookRepository.deleteById(idx);
    }

}