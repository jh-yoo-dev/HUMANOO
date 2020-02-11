package com.example.bookstore.model.entitiy;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
@ToString
@Builder
public class Book extends TimeEntity{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idx;

    @Column
    private String id;

    @Column
    private String ISBN;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String categories;

}