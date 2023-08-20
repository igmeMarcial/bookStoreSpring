package com.role.implementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.role.implementation.model.MyBookList;



@Repository
public interface MyBookRepository extends JpaRepository<MyBookList,Integer>{

}
