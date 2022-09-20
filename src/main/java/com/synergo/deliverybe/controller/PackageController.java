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

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    private Random RANDOM = new Random(1_000_000);
    @Autowired
    private PackageService packageService;

    @GetMapping
    public ResponseEntity<List<PackageDto>> fetchAllPackages() {
        List<Package> packages = packageService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(packages.stream().map(PackageDto::valueOf).toList());
    }

    @GetMapping("/customer")
    public ResponseEntity<List<PackageDto>> fetchAllPackagesByCustomer(@RequestParam(value = "customer_id") Integer customerId) {
        List<Package> packages = packageService.getAllPackagesByCustomer(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(packages.stream().map(PackageDto::valueOf).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Package>> fetchPackageById(@PathVariable("id") Integer id) {
        Optional<Package> pack = packageService.getPackageById(id);
        return ResponseEntity.status(pack.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(pack.isPresent() ? pack : Optional.empty());
    }

    @PostMapping
    public ResponseEntity<PackageDto> addPackage(@RequestBody Package pack) {
        Package added = packageService.buildPackage(RANDOM.nextInt(), pack.getDepartureDate(),
                pack.getSenderName(), pack.getSenderPhone(), pack.getDepartureAddress(),
                pack.getAwb(), pack.getDeliveryAddress(), pack.getDeliveryDate(), pack.getRecipientName(), pack.getRecipientPhone());

        return ResponseEntity.status(HttpStatus.OK).body(PackageDto.valueOf(added));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Package>> updatePackage(@RequestBody Package pack, @PathVariable Integer id) {
        Optional<Package> newPackage = packageService.updatePackage(pack, id);
        return ResponseEntity.status(HttpStatus.OK).body(newPackage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePackage(@PathVariable Integer id) {
        String result;
        try {
            result = packageService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(result);
    }
}
