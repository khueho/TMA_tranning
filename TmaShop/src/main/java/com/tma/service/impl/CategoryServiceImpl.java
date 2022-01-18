package com.tma.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tma.dao.CategoryDao;
import com.tma.entity.Category;
import com.tma.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	CategoryDao categoryDao ;

	public CategoryServiceImpl(CategoryDao categoryDao) {
		
		this.categoryDao = categoryDao;
	}
	
	

	@Override
	public List<Category> findByNameContaining(String name) {
		return categoryDao.findByNameContaining(name);
	}



	@Override
	public <S extends Category> S save(S entity) {
		return categoryDao.save(entity);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryDao.findAll(pageable);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryDao.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<Long> ids) {
		return categoryDao.findAllById(ids);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryDao.findById(id);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryDao.saveAll(entities);
	}

	@Override
	public void flush() {
		categoryDao.flush();
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryDao.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return categoryDao.existsById(id);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryDao.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryDao.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return categoryDao.exists(example);
	}

	@Override
	public long count() {
		return categoryDao.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		categoryDao.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Category entity) {
		categoryDao.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		categoryDao.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		categoryDao.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoryDao.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoryDao.deleteAll();
	}

	@Override
	public Category getById(Long id) {
		return categoryDao.getById(id);
	}
	
}
