package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CarDto;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars/all")
    public ResponseEntity<List<CarDto>> fetchAllCars() {
        List<Car> cars = carService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(cars.stream().map(CarDto::valueOf).toList());
    }
}
