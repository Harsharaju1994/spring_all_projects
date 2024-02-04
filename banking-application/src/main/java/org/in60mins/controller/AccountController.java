package org.in60mins.controller;

import lombok.NoArgsConstructor;
import org.in60mins.dto.AccountDto;
import org.in60mins.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> postAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountNumber){
        AccountDto accountDto = accountService.findById(accountNumber);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long accountNumber,
                                              @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(accountNumber,amount);
        return  ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long accountNumber,
                                               @RequestBody Map<String,Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(accountNumber,amount);
        return  ResponseEntity.ok(accountDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return  ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber){
        accountService.deleteAccount(accountNumber);
        return ResponseEntity.ok("Account is deleted successfully!");
    }
}
