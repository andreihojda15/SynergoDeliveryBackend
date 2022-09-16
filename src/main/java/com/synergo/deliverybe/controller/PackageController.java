package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.PackageDto;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
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

    @GetMapping("packages/customer")
    public ResponseEntity<List<PackageDto>> fetchAllPackagesByCustomer(@RequestParam(value = "customer_id") Integer customerId) {
        List<Package> packages = packageService.getAllPackagesByCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(packages.stream().map(PackageDto::valueOf).toList());
    }

    @GetMapping("/packages/{id}")
    public ResponseEntity<Optional<Package>> fetchPackageById(@PathVariable("id") Integer id) {
        Optional<Package> pack = packageService.getPackageById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pack);
    }

    @PostMapping("packages/add")
    public ResponseEntity<PackageDto> addPackage(@RequestBody Package pack) {
        Car car = new Car();
        car.setId(RANDOM.nextInt());
        System.out.println("Pack : " + pack);
        Package added = packageService.buildPackage(RANDOM.nextInt(), pack.getDeparture_date(), car,
        pack.getSender_name(), pack.getSender_phone(), pack.getDeparture_address(),
        pack.getAwb(), pack.getDelivery_address(), pack.getDelivery_date(), pack.getRecipient_name(), pack.getRecipient_phone());

        return ResponseEntity.status(HttpStatus.OK).body(PackageDto.valueOf(added));
    }

    @PutMapping("packages/{id}")
    public ResponseEntity<Optional<Package>> updatePackage(@RequestBody Package pack, @PathVariable Integer id) {
        Optional<Package> newPackage = packageService.updatePackage(pack, id);
        return ResponseEntity.status(HttpStatus.OK).body(newPackage);
    }

    @DeleteMapping("packages/{id}")
    public ResponseEntity<?> deletePackage(@PathVariable Integer id) {
        packageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
