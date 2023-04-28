package com.example.bankserverproject.service;

import com.example.bankserverproject.domain.dto.CustomerDTO;
import com.example.bankserverproject.domain.model.Customer;
import com.example.bankserverproject.repository.AccountRepository;
import com.example.bankserverproject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::convertToCustomerDTO);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDto) {
        Customer customer = convertToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToCustomerDTO(savedCustomer);
//        return savedCustomer;
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDto) {
        Customer customer = convertToCustomer(customerDto);
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToCustomerDTO(updatedCustomer);
//        return updatedCustomer;
    }

    public void deleteCustomerById(Long id) {
        // Check if customer exists
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));

        // Delete the customer's associated account
//        Account account = customer.getAccount();
//        if (account != null) {
//            // Delete the account
//            accountRepository.deleteById(account.getId());
//        }

        // Delete the customer
        customerRepository.deleteById(id);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setContactNumber(customer.getContactNumber());
//        Account account = customer.getAccount();
//        if (account != null) {
//            AccountDTO accountDto = new AccountDTO();
//            accountDto.setId(account.getId());
//            accountDto.setAccountNumber(account.getAccountNumber());
//            // Set the accountDto in customerDto
//            customerDto.setAccountId(accountDto.getId());
//        }
        return customerDto;
    }

    private Customer convertToCustomer(CustomerDTO customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setContactNumber(customerDto.getContactNumber());
//        Long accountDto = customerDto.getAccountId();
        return customer;
    }
}