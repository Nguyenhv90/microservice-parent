package com.hvn.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hvn.loans.config.LoansServiceConfig;
import com.hvn.loans.model.Customer;
import com.hvn.loans.model.Loans;
import com.hvn.loans.model.Properties;
import com.hvn.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoansController {
    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    LoansServiceConfig loansConfig;

    @PostMapping("/myLoans")
    public ResponseEntity<List<Loans>> getAccountDetails(@RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());
        if (loans != null) {
            return ResponseEntity.ok(loans);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
                loansConfig.getMailDetails(), loansConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
