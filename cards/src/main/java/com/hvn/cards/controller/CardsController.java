package com.hvn.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hvn.cards.config.CardServiceConfig;
import com.hvn.cards.model.Cards;
import com.hvn.cards.model.Customer;
import com.hvn.cards.model.Properties;
import com.hvn.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    CardServiceConfig cardConfig;

    @PostMapping("/myCards")
    public ResponseEntity<List<Cards>> getAccountDetails(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (!cards.isEmpty()) {
            return ResponseEntity.ok(cards);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardConfig.getMsg(), cardConfig.getBuildVersion(),
                cardConfig.getMailDetails(), cardConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
