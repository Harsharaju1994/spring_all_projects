package org.in60mins.service.impl;

import org.in60mins.dto.AccountDto;
import org.in60mins.entity.Account;
import org.in60mins.mapper.AccountMapper;
import org.in60mins.repository.AccountRepository;
import org.in60mins.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto findById(Long accountNumber) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(()->new RuntimeException("Account number is not available..."));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long accountNumber, double amount) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()->new RuntimeException("Account number is not available..."));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long accountNumber, double amount) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()->
                        new RuntimeException("Account number is not available..."));

        if (account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts
                .stream()
                .map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long accountNumber) {
        Account account = accountRepository
                .findById(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account number is not available..."));

        accountRepository.deleteById(accountNumber);
    }


}
