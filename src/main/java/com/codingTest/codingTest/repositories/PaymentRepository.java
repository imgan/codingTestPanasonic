package com.codingTest.codingTest.repositories;

import com.codingTest.codingTest.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Payment findByCreditCardNumber(String creditCardNumber);
}
