package org.in60mins.mapper;

import org.in60mins.dto.AccountDto;
import org.in60mins.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getAccountNumber(),
                accountDto.getAcountHolderName(),
                accountDto.getBalance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getAccountNumber(),
                account.getAcountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
