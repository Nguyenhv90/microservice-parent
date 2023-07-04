package com.hvn.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hvn.accounts.config.AccountsServiceConfig;
import com.hvn.accounts.model.*;
import com.hvn.accounts.repository.AccountRepository;
import com.hvn.accounts.service.client.CardsFeignClient;
import com.hvn.accounts.service.client.LoansFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountsServiceConfig accountsConfig;

    @Autowired
    LoansFeignClient loansFeignClient;
    @Autowired
    CardsFeignClient cardsFeignClient;

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

    @PostMapping("/customerDetails")
    @CircuitBreaker(name = "customerDetailsSupportApp")
    public CustomerDetails getCustomerDetails(@RequestBody Customer customer) {
        Account account = accountRepository.findByCustomerId(customer.getCustomerId());
        List<Cards> cards = cardsFeignClient.getCardDetails(customer).getBody();
        List<Loans> loans = loansFeignClient.getLoansDetails(customer).getBody();

        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setAccounts(account);
        customerDetails.setCards(cards);
        customerDetails.setLoans(loans);
        return customerDetails;
    }
}
