package com.example.bankserverproject.controller;

import com.example.bankserverproject.domain.dto.CustomerDTO;
import com.example.bankserverproject.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    //    Logger logger= LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        try{
            CustomerDTO customer = customerService.getCustomerById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
//            log.info("Get customer with ID: {}", customer);
            return ResponseEntity.ok(customer);
        }catch(Exception e) {
//            log.error(String.valueOf(e));
            return ResponseEntity.noContent().header("Content-Length", "0").build();
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDto) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDto);
        log.info("Customer created: {}", createdCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDto) {
        customerDto.setId(id);
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerDto);
        log.info("Customer updated: {}", updatedCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        try{
            customerService.deleteCustomerById(id);
            log.info("Customer deleted with ID: {}",id);
        }catch(Exception e){
            System.out.println(String.valueOf(e));
        }
        return ResponseEntity.noContent().build();
    }
}
