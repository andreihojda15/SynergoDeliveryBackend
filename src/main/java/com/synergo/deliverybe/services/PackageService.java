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

import javax.persistence.EntityNotFoundException;
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

    public Package buildPackage(Package pack) {
        Customer customer = customerRepo.findByName(pack.getRecipientName());
        Package packToBuild = new Package();

        packToBuild.setSenderName(pack.getSenderName());
        packToBuild.setSenderPhoneNumber(pack.getSenderPhoneNumber());
        packToBuild.setDepartureAddress(pack.getDepartureAddress());
        packToBuild.setDepartureDate(pack.getDepartureDate());
        packToBuild.setAwb(pack.getAwb());
        packToBuild.setDeliveryAddress(pack.getDeliveryAddress());
        packToBuild.setDeliveryDate(pack.getDeliveryDate());
        packToBuild.setRecipientName(pack.getRecipientName());
        packToBuild.setRecipientPhone(pack.getRecipientPhone());
        packToBuild.setCustomer(customer);
        packToBuild.setCar(null);

        return packageRepo.save(packToBuild);
    }

    public List<Package> getAllPackagesByCustomer(Integer customerId) {
        return packageRepo.getPackagesByCustomer(customerRepo.getReferenceById(customerId));
    }

    public Optional<Package> getPackageById(Integer id) {
        return packageRepo.findById(id);
    }

    public Optional<Package> deleteById(Integer id) {
        Optional<Package> packageToDelete = packageRepo.findById(id);
        if(packageToDelete.isEmpty())
            throw new EntityNotFoundException("Can't delete package because it doesn't exist.");
        packageRepo.deleteById(id);
        return packageToDelete;
    }

    public Optional<Package> updatePackage(Package pack, Integer id) {
        return packageRepo.findById(id).map(element -> {
            if (pack.getSenderName().length() != 0) {
                element.setSenderName(pack.getSenderName());
                // if driver has been changed, update car_id of package
                String senderName = pack.getSenderName();
                Optional<Driver> driver = driverRepo.findByName(senderName);
                if (driver.isPresent())
                    element.setCar(driver.get().getCar());
                else element.setCar(null);
            }
            if (pack.getSenderPhoneNumber().length() != 0)
                element.setSenderPhoneNumber(pack.getSenderPhoneNumber());
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
