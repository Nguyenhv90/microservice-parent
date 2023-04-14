package com.hvn.accounts.controller;

import com.hvn.accounts.model.Account;
import com.hvn.accounts.model.Customer;
import com.hvn.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/myAccount")
    public ResponseEntity<Account> getAccountDetails(@RequestBody Customer customer) {
        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }
}
