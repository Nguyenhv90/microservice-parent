package com.hvn.loans.controller;

import com.hvn.loans.model.Customer;
import com.hvn.loans.model.Loans;
import com.hvn.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoansController {
    @Autowired
    private LoansRepository loansRepository;

    @PostMapping("/myLoans")
    public ResponseEntity<List<Loans>> getAccountDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());
        if (loans != null) {
            return ResponseEntity.ok(loans);
        }
        return ResponseEntity.notFound().build();
    }
}
