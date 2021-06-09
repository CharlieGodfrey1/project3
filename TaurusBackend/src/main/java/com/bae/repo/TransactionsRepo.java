package com.bae.repo;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.EposTransaction;

@Repository
public interface TransactionsRepo extends JpaRepository<EposTransaction, Long> {

	Set<EposTransaction> findByTimestamp(LocalDateTime timestamp);

	Set<EposTransaction> findByPayeeAccount(Long payeeAccount);

	Set<EposTransaction> findAllBytimestampBetween(LocalDateTime time1, LocalDateTime time2);

	Set<EposTransaction> findAllBytimestampBetweenAndEposIdGreaterThan(LocalDateTime time1, LocalDateTime time2,
			Long eposId);

	Set<EposTransaction> findAllBytimestampBetweenAndBankCardNumber(LocalDateTime time1, LocalDateTime time2,
			Long bankCardNumber);

}
