package com.hvn.accounts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @Column(name="account_number")
    private long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "branch_address")
    private String branchAddress;
    @Column(name = "create_date")
    private LocalDate createDate;
}
