package com.synergo.deliverybe.services;


import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.CarRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class DriverService {

    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private CarRepo carRepo;

    public List<Driver> getAll() {
        return driverRepo.findAll();
    }


    public Driver buildDriver(Integer id, String name, String phoneNumber) {
        Driver driver = new Driver();

        driver.setId(id);
        driver.setName(name);
        driver.setPhoneNumber(phoneNumber);

        return driverRepo.save(driver);
    }

    public Optional<Driver> updateById(Driver driver, Integer id) {
        return driverRepo.findById(id).map(e -> {
            e.setName(driver.getName());
            e.setPhoneNumber(driver.getPhoneNumber());

            return driverRepo.save(e);
        });
    }

    public Driver deleteById(Integer id) throws NotFoundException {
        Optional<Driver> driverToDelete = driverRepo.findById(id);
        if (!driverToDelete.isPresent()) {
            throw new NotFoundException("id" + id + "not present");
        }
        driverRepo.deleteById(id);
        return driverToDelete.get();
    }

    public Driver manageDriver(Integer idDriver, Integer idCar) throws EntityNotFoundException {
        Optional<Car> carOptional = carRepo.findById(idCar);
        if (carOptional.isEmpty()) {
            throw new EntityNotFoundException("Car not found");
        }

        Optional<Driver> driver = driverRepo.findById(idDriver).map(driver1 -> {
            if (driver1.getCar() != null && driver1.getCar().getId().equals(carOptional.get().getId()) && carOptional.get().getStatus().equals("Not Available")) {
                driver1.setCar(null);
                carOptional.map(car -> {
                    car.setStatus("Available");
                    return carRepo.save(car);
                });
                return driverRepo.save(driver1);
            }
            if (driver1.getCar() == null && carOptional.get().getStatus().equals("Available")) {
                carOptional.map(car -> {
                    car.setStatus("Not Available");
                    return carRepo.save(car);
                });
                driver1.setCar(carOptional.get());
            }

            return driverRepo.save(driver1);
        });
        return driver.get();
    }

    public List<Car> getAvailableCars() {
        List<Car> all = carRepo.findAll();
        Stream<Car> a = all.stream().filter(car -> Objects.equals(car.getStatus(), "Available"));
        return a.toList();
    }
}
