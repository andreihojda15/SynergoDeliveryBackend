package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Car;
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
        pack.setSender_name(senderName);
        pack.setSender_phone(senderPhoneNo);
        pack.setDeparture_address(departureAddress);
        pack.setDeparture_date(departureDate);
        pack.setAwb(awb);
        pack.setDelivery_address(deliveryAddress);
        pack.setDelivery_date(deliveryDate);
        pack.setRecipient_name(recipientName);
        pack.setRecipient_phone(recipientPhoneNo);
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
            if (pack.getSender_name().length() != 0) {
                element.setSender_name(pack.getSender_name());
                // if driver has been changed, update car_id of package
                Driver driver = driverRepo.findByName(pack.getSender_name());
                if (driver != null)
                    element.setCar(driver.getCar());
                else element.setCar(null);
            }
            if (pack.getSender_phone().length() != 0)
                element.setSender_phone(pack.getSender_phone());
            if (pack.getDeparture_address().length() != 0)
                element.setDeparture_address(pack.getDeparture_address());
            if (pack.getDeparture_date() != null)
                element.setDeparture_date(pack.getDeparture_date());
            if (pack.getAwb().length() != 0)
                element.setAwb(pack.getAwb());
            if (pack.getDelivery_address().length() != 0)
                element.setDelivery_address(pack.getDelivery_address());
            if (pack.getDelivery_date() != null)
                element.setDelivery_date(pack.getDelivery_date());
            if (pack.getRecipient_name().length() != 0) {
                element.setRecipient_name(pack.getRecipient_name());
                // if customer name has changed, update customer
                Customer customer = customerRepo.findByName(pack.getRecipient_name());
                if (customer != null)
                    element.setCustomer(customer);
                else element.setCustomer(null);
            }
            if (pack.getRecipient_phone().length() != 0)
                element.setRecipient_phone(pack.getRecipient_phone());
            return packageRepo.save(element);
        });
    }

}
