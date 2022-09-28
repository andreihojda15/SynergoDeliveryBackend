package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.DriverDto;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        return ResponseEntity.status(HttpStatus.OK).body(drivers.stream().map(DriverDto::valueOf).toList());
    }

    @PostMapping
    public ResponseEntity<DriverDto> addDriver(@RequestBody Driver driver) {
        Driver added = driverService.buildDriver(driver.getId(), driver.getName(),
                driver.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.OK).body(DriverDto.valueOf(added));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Driver>> updateById(@RequestBody Driver driver, @PathVariable Integer id) {
        Optional<Driver> updatedDriver = driverService.updateById(driver, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDriver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer id) {
        driverService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
