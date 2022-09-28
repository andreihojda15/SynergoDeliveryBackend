package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.CarRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import com.synergo.deliverybe.repository.PackageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class CarService {
    @Autowired
    private CarRepo carRepo;

    @Autowired
    private DriverRepo driverRepo;

    @Autowired
    private PackageRepo packageRepo;

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

    public Car deleteById(Integer id) {
        Optional<Car> car = carRepo.findById(id);
        if (car.isEmpty()) {
            throw new EntityNotFoundException("Car not found");
        }

        Integer id1 = car.get().getId();
        Driver driver = driverRepo.findByCar_Id(id1);
        if (driver != null) {
            driver.setCar(null);
        }

        Package pack = packageRepo.findByCar_Id(id1);
        if (pack != null) {
            pack.setCar(null);
        }

        carRepo.deleteById(id);
        return car.get();
    }

    public Package managePackages(Integer idCar, Integer idPackage) throws Exception {
        Optional<Car> car = carRepo.findById(idCar);
        if (car.isEmpty()) {
            throw new EntityNotFoundException("Car not found");
        }
        Optional<Package> pack = packageRepo.findById(idPackage).map(pack1 -> {
            if (pack1.getCar() != null && pack1.getCar().getId().equals(car.get().getId())) {
                pack1.setCar(null);
                return packageRepo.save(pack1);
            }
            if (pack1.getCar() == null) {
                pack1.setCar(car.get());
            }
            return packageRepo.save(pack1);
        });
        return pack.get();
    }

    public List<Package> getAvailablePackages(Integer id) {
        if (carRepo.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Car not found");
        }
        List<Package> all = packageRepo.findAll();
        Stream<Package> a = all.stream().filter(pack -> pack.getCar() == null || pack.getCar().getId().equals(id));
        return a.toList();
    }
}
