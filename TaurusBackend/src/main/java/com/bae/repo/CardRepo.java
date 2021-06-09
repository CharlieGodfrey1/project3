package com.bae.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.domain.BankCard;

@Repository
public interface CardRepo extends JpaRepository<BankCard, Long> {

	BankCard findAllByCardNumber(Long cardNumber);

}
