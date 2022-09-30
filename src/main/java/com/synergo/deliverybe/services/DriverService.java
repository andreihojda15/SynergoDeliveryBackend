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

    public Optional<Driver> updateById(Driver driver, Integer id){
        return driverRepo.findById(id).map(e -> {
         e.setName(driver.getName());
         e.setPhoneNumber(driver.getPhoneNumber());

         return driverRepo.save(e);
        });
    }

    public Driver deleteById(Integer id) throws NotFoundException {
        Optional<Driver> driverToDelete = driverRepo.findById(id);
        if(!driverToDelete.isPresent()){
            throw new NotFoundException("id" +id + "not present");
        }
        driverRepo.deleteById(id);
        return driverToDelete.get();
    }
//    public Car manageCars(Integer idDriver, Integer idCar) throws Exception {
//        Optional<Driver> driver = driverRepo.findById(idDriver);
//        if (driver.isEmpty()) {
//            throw new EntityNotFoundException("Car not found");
//        }
//        Optional<Car> car = carRepo.findById(idCar).map(pack1 -> {
//            if (pack1.getCar() != null && pack1.getCar().getId().equals(car.get().getId())) {
//                pack1.setCar(null);
//                return packageRepo.save(pack1);
//            }
//            if (pack1.getCar() == null) {
//                pack1.setCar(car.get());
//            }
//            return packageRepo.save(pack1);
//        });
//        return pack.get();
//    }

    public List<Car> getAvailableCars(Integer id) {
        if (driverRepo.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Driver not found");
        }
        List<Car> all = carRepo.findAll();
        Stream<Car> a = all.stream().filter(car -> Objects.equals(car.getStatus(), "Available"));
        return a.toList();
    }
}
