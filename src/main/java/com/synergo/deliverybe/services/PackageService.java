package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.CarRepo;
import com.synergo.deliverybe.repository.CustomerRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import com.synergo.deliverybe.repository.PackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepo packageRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private DriverRepo driverRepo;

    public List<Package> getAll() {
        return packageRepo.findAll();
    }

    public Package buildPackage(int id, LocalDate departureDate, String senderName,
                                String senderPhoneNo, String departureAddress, String awb,
                                String deliveryAddress, LocalDate deliveryDate, String recipientName, String recipientPhoneNo) {
        Customer customer = customerRepo.findByName(recipientName);
        Package pack = new Package();

        pack.setId(id);
        pack.setSenderName(senderName);
        pack.setSenderPhone(senderPhoneNo);
        pack.setDepartureAddress(departureAddress);
        pack.setDepartureDate(departureDate);
        pack.setAwb(awb);
        pack.setDeliveryAddress(deliveryAddress);
        pack.setDeliveryDate(deliveryDate);
        pack.setRecipientName(recipientName);
        pack.setRecipientPhone(recipientPhoneNo);
        pack.setCustomer(customer);
        pack.setCar(null);

        return packageRepo.save(pack);
    }

    public List<Package> getAllPackagesByCustomer(Integer customerId) {
        return packageRepo.getPackagesByCustomer(customerRepo.getReferenceById(customerId));
    }

    public Optional<Package> getPackageById(Integer id) {
        return packageRepo.findById(id);
    }

    public void deleteById(Integer id) {
        if(packageRepo.findById(id).isPresent())
            packageRepo.deleteById(id);
    }

    public Optional<Package> updatePackage(Package pack, Integer id) {
        return packageRepo.findById(id).map(element -> {
            if (pack.getSenderName().length() != 0) {
                element.setSenderName(pack.getSenderName());
                // if driver has been changed, update car_id of package
                Driver driver = driverRepo.findByName(pack.getSenderName());
                if (driver != null)
                    element.setCar(driver.getCar());
                else element.setCar(null);
            }
            if (pack.getSenderPhone().length() != 0)
                element.setSenderPhone(pack.getSenderPhone());
            if (pack.getDepartureAddress().length() != 0)
                element.setDepartureAddress(pack.getDepartureAddress());
            if (pack.getDepartureDate() != null)
                element.setDepartureDate(pack.getDepartureDate());
            if (pack.getAwb().length() != 0)
                element.setAwb(pack.getAwb());
            if (pack.getDeliveryAddress().length() != 0)
                element.setDeliveryAddress(pack.getDeliveryAddress());
            if (pack.getDeliveryDate() != null)
                element.setDeliveryDate(pack.getDeliveryDate());
            if (pack.getRecipientName().length() != 0) {
                element.setRecipientName(pack.getRecipientName());
                // if customer name has changed, update customer
                Customer customer = customerRepo.findByName(pack.getRecipientName());
                if (customer != null)
                    element.setCustomer(customer);
                else element.setCustomer(null);
            }
            if (pack.getRecipientPhone().length() != 0)
                element.setRecipientPhone(pack.getRecipientPhone());
            return packageRepo.save(element);
        });
    }

}
