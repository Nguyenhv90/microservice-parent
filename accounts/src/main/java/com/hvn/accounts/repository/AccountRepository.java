package com.hvn.accounts.repository;

import com.hvn.accounts.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByCustomerId(int customerId);
}
