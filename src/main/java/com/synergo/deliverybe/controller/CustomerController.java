package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CustomerDto;
import com.synergo.deliverybe.dto.DriverDto;
import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> fetchAllDrivers() {
        List<Customer> customers = customerService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(customers.stream().map(CustomerDto::valueOf).toList());
    }


}
