package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CustomerDto;
import com.synergo.deliverybe.dto.DriverDto;
import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.services.CustomerService;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> fetchAllDrivers() {
        List<Customer> customers = customerService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(customers.stream().map(CustomerDto::valueOf).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Integer id)
    {
        Customer customer = customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerDto.valueOf(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto)
    {
        Customer customer = CustomerDto.fromDto(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerDto.valueOf(customerService.addCustomer(customer)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Optional<Customer>> editCustomer(@PathVariable Integer id , @RequestBody Customer customer)
    {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.editCustomer(customer, id));
    }


}
