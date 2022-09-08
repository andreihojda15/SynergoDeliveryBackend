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

    public Package buildPackage(String departureDate, Car car, String... others) {
        return packageRepo.buildPackage(departureDate, car, others);
    }
}
