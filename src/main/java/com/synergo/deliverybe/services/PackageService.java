package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.PackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepo packageRepo;

    public List<Package> getAll() {
        return packageRepo.findAll();
    }

    public Package buildPackage(int id, String departureDate, Car car, String senderName,
                                String senderPhoneNo, String departureAddress, String awb,
                                String deliveryAddress, String deliveryDate, String recipientName, String recipientPhoneNo) {
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
//        pack.setCar(car);

        return packageRepo.save(pack);
    }
}
