package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CarDto;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> fetchAllCars() {
        List<Car> cars = carService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(cars.stream().map(CarDto::valueOf).toList());
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody Car car){
        return ResponseEntity.status(HttpStatus.OK).body(CarDto.valueOf(carService.addCar(car)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Car>> updateCar(@RequestBody Car car, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(carService.updateCar(car, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer id) {
        String result;
        try {
            result = carService.deleteById(id);
        }catch (EntityNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(result);
    }
}
