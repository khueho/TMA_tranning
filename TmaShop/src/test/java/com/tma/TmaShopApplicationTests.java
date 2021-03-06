package com.tma;

import static org.junit.Assert.assertNotNull;

import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tma.dao.CategoryDao;
import com.tma.entity.Category;
import org.junit.Assert;

@SpringBootTest
class TmaShopApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CategoryDao categorydao;
	
	@Test
	public void createCategoryTest() {
		Category category = new Category( );
		
		category.setName("xe dien");
				categorydao.save(category);
				assertNotNull(categorydao.findById(2L).get());
	}

	
}
