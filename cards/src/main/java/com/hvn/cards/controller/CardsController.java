package com.hvn.cards.controller;

import com.hvn.cards.model.Cards;
import com.hvn.cards.model.Customer;
import com.hvn.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @PostMapping("/myCards")
    public ResponseEntity<List<Cards>> getAccountDetails(@RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (!cards.isEmpty()) {
            return ResponseEntity.ok(cards);
        }
        return ResponseEntity.notFound().build();
    }
}
