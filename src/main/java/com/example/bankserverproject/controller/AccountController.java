package com.example.bankserverproject.controller;

import com.example.bankserverproject.domain.dto.AccountDTO;
import com.example.bankserverproject.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
@Slf4j
public class AccountController {

//    Logger logger= LoggerFactory.getLogger(AccountController.class); //

    private final AccountService accountService;

//    @GetMapping("{accountId}/transactions")
//    public ResponseEntity<List<TransactionDTO>>  getAllTransactions(@PathVariable("accountId") Long accountId) {
//        List<TransactionDTO> transactions = accountService.getAllTransactions(accountId);
////        log.info("user required all accounts");
//        return new ResponseEntity<>(transactions, HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDto) {
        System.out.println("First enter");
        try{
            System.out.println("Account succesfully returned");
            AccountDTO createdAccount = accountService.createAccount(accountDto);
            System.out.println("Account succesfully returned");
            log.info("Account created: {}",createdAccount); //
            System.out.println("Account succesfully returned");
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);

        }catch(Exception e){
            log.error(String.valueOf(e));
        }
        return ResponseEntity.noContent().header("Content-Length", "0").build();
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long accountId, @RequestBody AccountDTO accountDto) {
        accountDto.setId(accountId);
        AccountDTO updatedAccount = accountService.updateAccount(accountDto);
        log.info("Account updated: {}",updatedAccount);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        log.info("Account deleted with ID: {}",accountId); //
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts();
//        log.info("user required all accounts");
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Optional<AccountDTO>> getAccountById(@PathVariable Long accountId) {
        Optional<AccountDTO> account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }
}