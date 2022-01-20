package com.tma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tma.entity.Account;
import com.tma.entity.Product;

public interface AccountDao  extends JpaRepository<Account, String>{
	
}
