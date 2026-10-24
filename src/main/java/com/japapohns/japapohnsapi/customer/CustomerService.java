package com.japapohns.japapohnsapi.customer;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request){
        if (customerRepository.existsByPhoneNumber(request.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already exists");
        }

        CustomerEntity customer = new CustomerEntity();

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setAddress(request.address());
        customer.setSubdivision(request.subdivision());
        customer.setNearestIntersection(request.nearestIntersection());
        customer.setCardType(request.cardType());

        customerRepository.save(customer);

        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getSubdivision(),
                customer.getNearestIntersection(),
                customer.getCardType());
    }

    public CustomerResponse updateCustomer(String phoneNumber, CustomerRequest request){
        if (!phoneNumber.equals(request.phoneNumber()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number does not match request");

        CustomerEntity customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setPhoneNumber(request.phoneNumber());
        customer.setAddress(request.address());
        customer.setSubdivision(request.subdivision());
        customer.setNearestIntersection(request.nearestIntersection());
        customer.setCardType(request.cardType());

        customerRepository.save(customer);

        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getSubdivision(),
                customer.getNearestIntersection(),
                customer.getCardType());
    }

    public void deleteCustomer(String phoneNumber){
       CustomerEntity customer = customerRepository
               .findByPhoneNumber(phoneNumber)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

       customerRepository.delete(customer);
    }

    public CustomerResponse getCustomerByPhoneNumber(String phoneNumber){
        CustomerEntity customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getSubdivision(),
                customer.getNearestIntersection(),
                customer.getCardType());
    }

    public List<CustomerResponse> getCustomersByLastName(String lastName){
        List<CustomerEntity> customers = customerRepository.findByLastNameIgnoreCase(lastName);

        return customers.stream().map(customer -> new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getSubdivision(),
                customer.getNearestIntersection(),
                customer.getCardType()
        )).toList();
    }

}
