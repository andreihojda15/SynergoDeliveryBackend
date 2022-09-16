package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Repository
@Deprecated
class PackageRepoOld {
    private Random RANDOM = new Random(1_000_000);
    List<Package> packages = new ArrayList<>();

    public List<Package> getAll() {
        return packages;
    }

    public Package buildPackage(String departureDate, Car car, String senderName, String senderPhoneNo,
                                String departureAddress, String awb, String deliveryAddress, String deliveryDate, String recipientName, String recipientPhoneNo) {
        Package pack = new Package();

        pack.setId(RANDOM.nextInt());
        pack.setSender_name(senderName);
        pack.setSender_phone(senderPhoneNo);
        pack.setDeparture_address(departureAddress);
        pack.setDeparture_date(Instant.parse(departureDate));
        pack.setAwb(awb);
        pack.setDelivery_address(deliveryAddress);
        pack.setDelivery_date(Instant.parse(deliveryDate));
        pack.setRecipient_name(recipientName);
        pack.setRecipient_phone(recipientPhoneNo);
        packages.add(pack);

        return pack;
    }
}
