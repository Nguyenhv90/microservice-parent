package com.hvn.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hvn.accounts.config.AccountsServiceConfig;
import com.hvn.accounts.model.Account;
import com.hvn.accounts.model.Customer;
import com.hvn.accounts.model.Properties;
import com.hvn.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountsServiceConfig accountsConfig;

    @PostMapping("/myAccount")
    public ResponseEntity<Account> getAccountDetails(@RequestBody Customer customer) {
        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        if (account != null) {
            return ResponseEntity.ok(account);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(),
                accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
