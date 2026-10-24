package com.japapohns.japapohnsapi.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request){
        CustomerResponse customer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(customer);
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String phoneNumber){
        return ResponseEntity.ok(customerService.getCustomerByPhoneNumber(phoneNumber));
    }

    @PutMapping("/{phoneNumber}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable String phoneNumber, @RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.updateCustomer(phoneNumber, request));
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String phoneNumber){
        customerService.deleteCustomer(phoneNumber);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponse>> searchCustomers(@RequestParam String lastName){
        return ResponseEntity.ok(customerService.getCustomersByLastName(lastName));
    }
}
