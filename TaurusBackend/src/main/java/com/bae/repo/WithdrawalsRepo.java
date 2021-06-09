package com.bae.repo;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.AtmTransaction;

@Repository
public interface WithdrawalsRepo extends JpaRepository<AtmTransaction, Long> {

	Set<AtmTransaction> findAllBytimestampBetweenAndAtmIdGreaterThan(LocalDateTime time1, LocalDateTime time2,
			Long atmId);

}
