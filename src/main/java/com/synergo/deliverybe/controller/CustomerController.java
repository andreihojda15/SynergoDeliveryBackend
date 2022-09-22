package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CustomerDto;
import com.synergo.deliverybe.dto.DriverDto;
import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Integer id)
    {
        Customer customer = customerService.getCustomerbyId(id);
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerDto.valueOf(customer));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody Customer customer)
    {
        Customer customer1 = customerService.addCustomer("nameTest","addresseTest", "+1 500-400-1300");
        return ResponseEntity.status(HttpStatus.OK).body(CustomerDto.valueOf(customer1));
    }


    @PutMapping("/edite/{id}")
    public ResponseEntity<CustomerDto> editCustomer(@PathVariable Integer id ,@RequestBody Customer customer)
    {
        Customer customer1 = customerService.editCustomer(id,"nameEdit","addresseEdit", "+1 900-800-1000");
        return ResponseEntity.status(HttpStatus.OK).body(CustomerDto.valueOf(customer1));
    }


}
