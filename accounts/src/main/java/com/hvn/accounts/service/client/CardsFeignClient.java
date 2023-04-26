package com.hvn.accounts.service.client;

import com.hvn.accounts.model.Cards;
import com.hvn.accounts.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "cards/myCards", consumes = "application/json")
    ResponseEntity<List<Cards>> getCardDetails(@RequestBody Customer customer);
}
