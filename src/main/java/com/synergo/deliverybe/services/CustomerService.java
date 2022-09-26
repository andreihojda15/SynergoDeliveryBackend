package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.repository.CustomerRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getAll() {
        log.debug("Someone called us");
        return customerRepo.findAll();
    }

    public void deleteCustomer(Integer id)
    {
        customerRepo.deleteById(id);
    }

    public Customer getCustomerbyId(Integer id)
    {
        return customerRepo.getReferenceById(id);
    }

    public Customer addCustomer(Customer customer)
    {

        Customer customer1 = new Customer();
        customer1.setName(customer.getName());
        customer1.setAddress(customer.getAddress());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        return customerRepo.save(customer1);
    }

    public Customer editCustomer(int id,String name,String address, String phone)
    {
        Customer customer = getCustomerbyId(id);
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhoneNumber(phone);
        return customerRepo.save(customer);
    }

}
