package com.example.bookstore.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

@Entity
public class Book {
    @Id
    @GeneratedValue
    int id;

}