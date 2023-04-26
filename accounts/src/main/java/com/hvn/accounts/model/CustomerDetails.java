package com.hvn.accounts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    private Account accounts;
    private List<Loans> loans;
    private List<Cards> cards;
}
