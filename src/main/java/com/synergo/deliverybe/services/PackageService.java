package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.CustomerRepo;
import com.synergo.deliverybe.repository.PackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepo packageRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public List<Package> getAll() {
        return packageRepo.findAll();
    }

    public Package buildPackage(int id, String departureDate, Car car, String senderName,
                                String senderPhoneNo, String departureAddress, String awb,
                                String deliveryAddress, String deliveryDate, String recipientName, String recipientPhoneNo) {
        Customer c = customerRepo.findByName(recipientName);
        Package pack = new Package();
        pack.setId(id);
        pack.setSender_name(senderName);
        pack.setSender_phone(senderPhoneNo);
        pack.setDeparture_address(departureAddress);
        pack.setDeparture_date(departureDate);
        pack.setAwb(awb);
        pack.setDelivery_address(deliveryAddress);
        pack.setDelivery_date(deliveryDate);
        pack.setRecipient_name(recipientName);
        pack.setRecipient_phone(recipientPhoneNo);
        pack.setCustomer(c);

        return packageRepo.save(pack);
    }

    public List<Package> getAllPackagesByCustomer(Integer customerId) {
        return packageRepo.getPackagesByCustomer(customerRepo.getReferenceById(customerId));
    }

    public Optional<Package> getPackageById(Integer id) {
        return packageRepo.findById(id);
    }
}
