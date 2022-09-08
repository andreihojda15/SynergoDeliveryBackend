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

    public Package buildPackage(String departureDate, Car car, String... others) {
        Package pack = new Package();

        pack.setId(RANDOM.nextInt());
        pack.setSenderName(others[0]);
        pack.setSenderPhoneNo(others[1]);
        pack.setDepartureAddress(others[2]);
        pack.setDepartureDate(departureDate);
        pack.setAwb(others[3]);
        pack.setDeliveryAddress(others[4]);
        pack.setDeliveryDate(others[5]);
        pack.setRecipientName(others[6]);
        pack.setRecipientPhoneNo(others[7]);
        pack.setCar(car);
        packages.add(pack);

        return pack;
    }
}
