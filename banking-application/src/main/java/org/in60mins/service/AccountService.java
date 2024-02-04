package org.in60mins.service;

import org.in60mins.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto findById(Long id);
    AccountDto deposit(Long accountNumber,double amount);
    AccountDto withdraw(Long accountNumber,double amount);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long accountNumber);
}
