package com.synergo.deliverybe.services;


import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.repository.CarRepo;
import com.synergo.deliverybe.repository.DriverRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

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
}
