package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.PackageDto;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Random;

@Controller
public class PackageController {

    private Random RANDOM = new Random(1_000_000);
    @Autowired
    private PackageService packageService;

    @GetMapping("packages/all")
    public ResponseEntity<List<PackageDto>> fetchAllPackages() {
        List<Package> packages = packageService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(packages.stream().map(PackageDto::valueOf).toList());
    }

    @PostMapping("packages/add")
    public ResponseEntity<PackageDto> addPackage(@RequestBody Package pack) {
        Car car = new Car();
        car.setId(RANDOM.nextInt());

        Package added = packageService.buildPackage(pack.getDepartureDate(), car,
        pack.getSenderName(), pack.getSenderPhoneNo(), pack.getDepartureAddress(),
        pack.getAwb(), pack.getDeliveryAddress(), pack.getDeliveryDate(), pack.getRecipientName(), pack.getRecipientPhoneNo());

        return ResponseEntity.status(HttpStatus.OK).body(PackageDto.valueOf(added));
    }
}
