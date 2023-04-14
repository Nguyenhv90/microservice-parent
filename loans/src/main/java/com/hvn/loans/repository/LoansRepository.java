package com.hvn.loans.repository;

import com.hvn.loans.model.Loans;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoansRepository extends CrudRepository<Loans, Long> {
    List<Loans> findByCustomerIdOrderByStartDateDesc(int customerId);
}
