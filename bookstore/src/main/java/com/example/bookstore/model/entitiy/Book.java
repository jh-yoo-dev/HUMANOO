package com.example.bookstore.model.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "Book")
@ToString
public class Book extends TimeEntity{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idx;

    @Column(length =10 ,nullable = false)
    private String id;

    @Column
    private String ISBN;

    @Column(length =32 ,nullable = false)
    private String title;

    @Column
    private String author;

    @Column
    private String categories;

    @Builder
    public Book(final Long idx, final String id, final String ISBN, final String title, final String author,
            final String categories) {
        this.idx = idx;
        this.id = id;
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.categories = categories;
    }
}