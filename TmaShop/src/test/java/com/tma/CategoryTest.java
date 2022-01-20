package com.tma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tma.dao.CategoryDao;
import com.tma.entity.Category;
@SpringBootTest
public class CategoryTest {
	@Autowired
	private CategoryDao categorydao;
	
	@Test
	public void createCategoryTest() {
		Category category = new Category( );
		
		category.setName("xe dien");
				categorydao.save(category);
				assertNotNull(categorydao.findById(2L).get());
	}
	
	@Test
	public void updateCategoryTest() {
		Category category = categorydao.findById(2L).get();
		category.setName("tau dien");
		categorydao.save(category);
				assertNotEquals("xe dien", categorydao.findById(2L).get().getCategoryId());
	}
	
	@Test
	public void deleteCategoryTest() {
		categorydao.deleteById(3L);
		assertThat(categorydao.existsById(3L));
	}
}
