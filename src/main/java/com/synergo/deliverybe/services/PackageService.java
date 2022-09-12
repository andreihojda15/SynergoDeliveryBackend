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
        return packageRepo.getAll();
    }

    public Package buildPackage(String departureDate, Car car, String senderName,
                                String senderPhoneNo, String departureAddress, String awb,
                                String deliveryAddress, String deliveryDate, String recipientName, String recipientPhoneNo) {
        return packageRepo.buildPackage(departureDate, car, senderName, senderPhoneNo,
                departureAddress, awb, deliveryAddress, deliveryDate, recipientName, recipientPhoneNo);
    }
}
