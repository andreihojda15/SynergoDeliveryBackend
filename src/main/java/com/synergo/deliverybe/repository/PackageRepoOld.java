package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;

import java.time.Instant;
import java.time.LocalDate;
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
        pack.setSenderName(senderName);
        pack.setSenderPhone(senderPhoneNo);
        pack.setDepartureAddress(departureAddress);
        pack.setDepartureDate(LocalDate.parse(departureDate));
        pack.setAwb(awb);
        pack.setDeliveryAddress(deliveryAddress);
        pack.setDeliveryDate(LocalDate.parse(deliveryDate));
        pack.setRecipientName(recipientName);
        pack.setRecipientPhone(recipientPhoneNo);
        packages.add(pack);

        return pack;
    }
}
