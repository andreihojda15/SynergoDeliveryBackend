package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.CustomerRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import com.synergo.deliverybe.repository.PackageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PackageRepo packageRepo;

    public List<Customer> getAll() {
        log.debug("Someone called us");
        return customerRepo.findAll();
    }

    public Customer deleteCustomer(Integer id)
    {
        Optional<Customer> customer = customerRepo.findById(id);

        List<Package> pack = packageRepo.findByCustomer_Id(customer.get().getId());
        if (pack != null) {
            for (Package aPackage : pack) {
//                aPackage.setCustomer(null);
                packageRepo.deleteById(aPackage.getId());
//                packageRepo.save(aPackage);
            }
        }

        customerRepo.deleteById(id);
        return customer.get();
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
