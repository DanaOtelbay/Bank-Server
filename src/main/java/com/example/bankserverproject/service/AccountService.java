package com.example.bankserverproject.service;

import com.example.bankserverproject.domain.dto.AccountDTO;
import com.example.bankserverproject.domain.model.Account;
import com.example.bankserverproject.domain.model.Customer;
import com.example.bankserverproject.repository.AccountRepository;
import com.example.bankserverproject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

//    public List<TransactionDTO> getAllTransactions(Long accountId) {
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountId));
//        List <TransactionDTO> transactionDtos = new ArrayList<>();
//        account.getTransactions().forEach((e) -> transactionDtos.add(TransactionDTO.fromTransaction(e)));
//        return transactionDtos;
//    }

    public AccountDTO createAccount(AccountDTO accountDto) {
        // Check if customer exists
        try{
            Customer customer = customerRepository.findById(accountDto.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + accountDto.getCustomerId()));
            if (accountRepository.existsByCustomer(customer)) {
                throw new IllegalStateException("Customer already has an account");
            }
            // Convert AccountDto to Account
            Account account = new Account();
            account.setAccountNumber(accountDto.getAccountNumber());
            account.setCustomer(customer);
            account.setCurrentBalance(accountDto.getCurrentBalance());
            System.out.println(customer);
//            customer.setAccount(account);
//        customerRepository.save(customer);

            // Save Account and get the saved Account with generated ID
            account = accountRepository.save(account);

            // Convert Account to AccountDto
//            System.out.println("Succesfully account created");
//            System.out.println(account.getCustomer());
            return convertToAccountDTO(account);

//            return account;
        }catch (Exception e){
            log.error(String.valueOf(e));
        }
        return new AccountDTO();
    }


    public AccountDTO updateAccount(AccountDTO accountDto) {
        // Convert AccountDto to Account
        Account account = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found with ID: " + accountDto.getId()));
//        Account account = new Account();
        account.setId(accountDto.getId());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setCurrentBalance(accountDto.getCurrentBalance());

        // Save updated Account and get the saved Account
        account = accountRepository.save(account);
//        System.out.println(account);
        // Convert Account to AccountDto
        return convertToAccountDTO(account);
//        return account;
    }

    public void deleteAccount(Long accountId) {
        // Delete Account by ID
        accountRepository.deleteById(accountId);
    }

    // Helper method to convert Account to AccountDto
    private AccountDTO convertToAccountDTO(Account account) {
//        System.out.println(account);
        AccountDTO accountDto = new AccountDTO();
        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
//        System.out.println(account.getCustomer().getId());
        accountDto.setCustomerId(account.getCustomer().getId());
        accountDto.setCreatedDate(account.getCreatedDate());
//        accountDto.setTransactions(TransactionDTO.fromTransactionList(account.getTransactions()));
        accountDto.setCurrentBalance(account.getCurrentBalance());
        return accountDto;
    }

    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            accountDtos.add(convertToAccountDTO(account));
        }
        return accountDtos;
    }

    public Optional<AccountDTO> getAccountById(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        return optionalAccount.map(this::convertToAccountDTO);
    }

    public Account getAccount(String accountNumber) {
        System.out.println("AccountNumber" + accountNumber);
        Optional<Account> account = accountRepository
                .findByAccountNumber(accountNumber);

        return account.orElse(null);
    }
}
