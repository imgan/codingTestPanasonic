package com.codingTest.codingTest.repositories;

import com.codingTest.codingTest.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository  extends JpaRepository<State,Integer> {
}
