package com.hvn.accounts.service.client;

import com.hvn.accounts.model.Customer;
import com.hvn.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "loans/myLoans", consumes = "application/json")
    ResponseEntity<List<Loans>> getLoansDetails(@RequestBody Customer customer);
}
