package com.bae.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.AccountHolder;

@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

	AccountHolder findByBankAccountId(Long bankAccountId);

}