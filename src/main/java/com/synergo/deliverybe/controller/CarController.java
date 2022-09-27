package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.dto.CarDto;
import com.synergo.deliverybe.dto.PackageDto;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto>> fetchAllCars() {
        List<Car> cars = carService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(cars.stream().map(CarDto::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@Valid @RequestBody CarDto carDto) {
        Car car = CarDto.fromDto(carDto);

        return ResponseEntity.status(HttpStatus.OK).body(CarDto.toDto(carService.addCar(car)));
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
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/managePackages/{id}")
    public ResponseEntity<?> managePackages(@PathVariable Integer id, @RequestParam Integer packageId) {
        String result;
        try {
            result = carService.managePackages(id, packageId);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/availablePackages/{id}")
    public ResponseEntity<?> getAvailablePackages(@PathVariable Integer id) {
        List<Package> availablePackages;
        try {
            availablePackages = carService.getAvailablePackages(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(availablePackages.stream().map(PackageDto::valueOf).toList());
    }
}
