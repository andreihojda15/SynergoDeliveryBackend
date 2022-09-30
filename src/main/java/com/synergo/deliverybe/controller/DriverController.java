package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CarDto;
import com.synergo.deliverybe.dto.DriverDto;
import com.synergo.deliverybe.dto.PackageDto;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDto>> fetchAllDrivers() {
        List<Driver> drivers = driverService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(drivers.stream().map(DriverDto::toDto).toList());
    }


    @PostMapping
    public ResponseEntity<DriverDto> addDriver(@RequestBody Driver driver) {
        Driver added = driverService.buildDriver(driver.getId(), driver.getName(),
                driver.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.OK).body(DriverDto.toDto(added));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDto> updateById(@RequestBody Driver driver, @PathVariable Integer id) {
        Optional<Driver> updatedDriver = driverService.updateById(driver, id);
        return ResponseEntity.status(HttpStatus.OK).body(DriverDto.toDto(updatedDriver.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer id) {
        Driver result;
        try {
            result = driverService.deleteById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("manageDriver/{idDriver}")
    public ResponseEntity<?> manageDriver(@PathVariable Integer idDriver, @RequestParam(name = "carId") Integer idCar) {
        Driver result;
        try {
            result = driverService.manageDriver(idDriver, idCar);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(DriverDto.toDto(result));
    }

    @GetMapping("/availableCars")
    public ResponseEntity<?> getAvailableCars() {
        List<Car> availableCars;
        try {
            availableCars = driverService.getAvailableCars();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(availableCars.stream().map(CarDto::toDto).toList());
    }
}
