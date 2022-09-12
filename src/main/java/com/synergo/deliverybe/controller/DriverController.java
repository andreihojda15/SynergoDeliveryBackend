package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.DriverDto;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/drivers/all")
    public ResponseEntity<List<DriverDto>> fetchAllDrivers() {
        List<Driver> drivers = driverService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(drivers.stream().map(DriverDto::valueOf).toList());
    }

}
