package com.example.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.example.bookstore.model.entitiy.Book;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDto {
    private Long idx;
    
    @NotBlank(message = "required field")
    private String id;

    @NotBlank(message = "required field")
    private String ISBN;

    @NotBlank(message = "required field")
    private String title;

    @NotBlank(message = "required field")
    private String author;

    @NotBlank(message = "required field")
    private String categories;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Book toEntity(){
        Book build = Book.builder()
            .idx(idx)
            .id(id)
            .ISBN(ISBN)
            .title(title)
            .author(author)
            .categories(categories)
            .build();
        return build;
    }

    @Builder
    public BookDto(Long idx, String id, String ISBN, String title, String author, String categories, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.idx = idx;
        this.id = id;
        this.ISBN = ISBN;
        this.title =title;
        this.author = author;
        this.categories = categories;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}