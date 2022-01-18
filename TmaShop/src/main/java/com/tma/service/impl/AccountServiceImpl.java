package com.tma.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tma.dao.AccountDao;
import com.tma.dao.CategoryDao;
import com.tma.entity.Account;
import com.tma.entity.Category;
import com.tma.service.AccountService;
import com.tma.service.CategoryService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao ;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public Account login(String username ,String password) {
		Optional<Account> optExist = findById(username);
		if (optExist.isPresent() && bcryptPasswordEncoder.matches(password,optExist.get().getPassword() )) {
			optExist.get().setPassword("");
			return optExist.get();
	}
		return null;
	}
	
	@Override
	public <S extends Account> S save(S entity) {
		Optional<Account> optExist = findById(entity.getUsername());
		if (optExist.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(optExist.get().getPassword());
			} else {
				entity.setPassword(bcryptPasswordEncoder.encode(entity.getPassword()));
			}
		}else { 
		entity.setPassword(bcryptPasswordEncoder.encode(entity.getPassword()));
		}
		return accountDao.save(entity);
	}
	@Override
	public <S extends Account> Optional<S> findOne(Example<S> example) {
		return accountDao.findOne(example);
	}
	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountDao.findAll(pageable);
	}
	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}
	@Override
	public List<Account> findAll(Sort sort) {
		return accountDao.findAll(sort);
	}
	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		return accountDao.findAllById(ids);
	}
	@Override
	public Optional<Account> findById(String id) {
		return accountDao.findById(id);
	}
	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return accountDao.saveAll(entities);
	}
	@Override
	public void flush() {
		accountDao.flush();
	}
	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		return accountDao.saveAndFlush(entity);
	}
	@Override
	public boolean existsById(String id) {
		return accountDao.existsById(id);
	}
	@Override
	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountDao.saveAllAndFlush(entities);
	}
	@Override
	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		return accountDao.findAll(example, pageable);
	}
	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		accountDao.deleteInBatch(entities);
	}
	@Override
	public <S extends Account> long count(Example<S> example) {
		return accountDao.count(example);
	}
	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		accountDao.deleteAllInBatch(entities);
	}
	@Override
	public <S extends Account> boolean exists(Example<S> example) {
		return accountDao.exists(example);
	}
	@Override
	public long count() {
		return accountDao.count();
	}
	@Override
	public void deleteById(String id) {
		accountDao.deleteById(id);
	}
	@Override
	public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return accountDao.findBy(example, queryFunction);
	}
	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountDao.deleteAllByIdInBatch(ids);
	}
	@Override
	public void delete(Account entity) {
		accountDao.delete(entity);
	}
	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		accountDao.deleteAllById(ids);
	}
	@Override
	public void deleteAllInBatch() {
		accountDao.deleteAllInBatch();
	}
	@Override
	public Account getOne(String id) {
		return accountDao.getOne(id);
	}
	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		accountDao.deleteAll(entities);
	}
	@Override
	public void deleteAll() {
		accountDao.deleteAll();
	}
	@Override
	public Account getById(String id) {
		return accountDao.getById(id);
	}
	@Override
	public <S extends Account> List<S> findAll(Example<S> example) {
		return accountDao.findAll(example);
	}
	@Override
	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
		return accountDao.findAll(example, sort);
	}
	@Override
	public int hashCode() {
		return bcryptPasswordEncoder.hashCode();
	}
	@Override
	public String encode(CharSequence rawPassword) {
		return bcryptPasswordEncoder.encode(rawPassword);
	}
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return bcryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}
	@Override
	public boolean upgradeEncoding(String encodedPassword) {
		return bcryptPasswordEncoder.upgradeEncoding(encodedPassword);
	}
	@Override
	public boolean equals(Object obj) {
		return bcryptPasswordEncoder.equals(obj);
	}
	@Override
	public String toString() {
		return bcryptPasswordEncoder.toString();
	}
	
	

	
}
