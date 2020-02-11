package com.example.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.example.bookstore.model.entitiy.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String userEmail);
}