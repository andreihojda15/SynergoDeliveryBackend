package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class PackageRepo {
    private Random RANDOM = new Random(1_000_000);
    List<Package> packages = new ArrayList<>();

    public List<Package> getAll() {
        return packages;
    }

    public Package buildPackage(String departureDate, Car car, String senderName, String senderPhoneNo,
                                String departureAddress, String awb, String deliveryAddress, String deliveryDate, String recipientName, String recipientPhoneNo) {
        Package pack = new Package();

        pack.setId(RANDOM.nextInt());
        pack.setSenderName(senderName);
        pack.setSenderPhoneNo(senderPhoneNo);
        pack.setDepartureAddress(departureAddress);
        pack.setDepartureDate(departureDate);
        pack.setAwb(awb);
        pack.setDeliveryAddress(deliveryAddress);
        pack.setDeliveryDate(deliveryDate);
        pack.setRecipientName(recipientName);
        pack.setRecipientPhoneNo(recipientPhoneNo);
        pack.setCar(car);
        packages.add(pack);

        return pack;
    }
}
