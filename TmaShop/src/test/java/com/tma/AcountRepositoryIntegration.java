package com.tma;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tma.dao.AccountDao;
import com.tma.entity.Account;

import lombok.Data;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AcountRepositoryIntegration {
	@Autowired
	private TestEntityManager testEntity;
	
	@Autowired
	private AccountDao accountDao ;
	 @Test 
	 private void whenFindByname() {
		Account account = new Account();
		account.setUsername("khue2");
		testEntity.persist(account);
		testEntity.flush();
		
		Optional<Account> found = accountDao.findById(account.getUsername());

		assertThat(found.get()).isEqualTo(account.getUsername());
	}
	 
	
}
