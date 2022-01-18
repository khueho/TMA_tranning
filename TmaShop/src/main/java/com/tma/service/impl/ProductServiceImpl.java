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
import org.springframework.stereotype.Service;

import com.tma.dao.ProductDao;
import com.tma.entity.Product;
import com.tma.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired ProductDao productDao ;

	@Override
	public <S extends Product> S save(S entity) {
		return productDao.save(entity);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productDao.findOne(example);
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productDao.findAll(pageable);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productDao.findAll(sort);
	}

	@Override
	public List<Product> findAllById(Iterable<Long> ids) {
		return productDao.findAllById(ids);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productDao.findById(id);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productDao.saveAll(entities);
	}

	@Override
	public void flush() {
		productDao.flush();
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return productDao.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return productDao.existsById(id);
	}

	@Override
	public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productDao.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDao.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		productDao.deleteInBatch(entities);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return productDao.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Product> entities) {
		productDao.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return productDao.exists(example);
	}

	@Override
	public long count() {
		return productDao.count();
	}

	@Override
	public void deleteById(Long id) {
		productDao.deleteById(id);
	}

	@Override
	public <S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productDao.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productDao.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Product entity) {
		productDao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productDao.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productDao.deleteAllInBatch();
	}

	@Override
	public Product getOne(Long id) {
		return productDao.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		productDao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		productDao.deleteAll();
	}

	@Override
	public Product getById(Long id) {
		return productDao.getById(id);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return productDao.findAll(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productDao.findAll(example, sort);
	}
	
}
