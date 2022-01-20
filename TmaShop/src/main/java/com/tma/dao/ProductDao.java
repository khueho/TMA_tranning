package com.tma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tma.entity.Account;
import com.tma.entity.Category;
import com.tma.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
	 
}
