package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.repository.CustomerRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAll() {
        return customerRepo.getAll();
    }
}
