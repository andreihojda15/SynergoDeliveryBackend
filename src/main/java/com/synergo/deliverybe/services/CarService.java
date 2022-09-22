package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.repository.CarRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CarService {
    @Autowired
    private CarRepo carRepo;

    public List<Car> getAll() {
        return carRepo.findAll();
    }

    public Car addCar(Car car) {
        return carRepo.save(car);
    }

    public Optional<Car> updateCar(Car car, Integer id) {
        return carRepo.findById(id).map(car1 -> {
           car1.setRegistrationNumber(car.getRegistrationNumber());
           car1.setStatus(car.getStatus());
           return carRepo.save(car1);
        });
    }

    public String deleteById(Integer id) {
        Optional<Car> car = carRepo.findById(id);
        if(car.isEmpty()) {
            throw new EntityNotFoundException("Car not found");
        }
        carRepo.deleteById(id);
        return "Successfully deleted";
    }
}
