package com.role.implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.role.implementation.model.Book;



@Repository
public interface BookRepository extends JpaRepository<Book,Integer>  {

}
