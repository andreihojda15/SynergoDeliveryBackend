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


    public Driver buildDriver(Integer id, String name, String phoneNumber, String status) {
        Driver driver = new Driver();

        driver.setId(id);
        driver.setName(name);
        driver.setPhoneNumber(phoneNumber);
        driver.setStatus(status);

        return driverRepo.save(driver);
    }

    public Optional<Driver> updateById(Driver driver, Integer id){
        return driverRepo.findById(id).map(e -> {
         e.setName(driver.getName());
         e.setPhoneNumber(driver.getPhoneNumber());
         e.setStatus(driver.getStatus());

         return driverRepo.save(e);
        });
    }

    public void deleteById(Integer id) throws NotFoundException {
        if(!driverRepo.findById(id).isPresent()){
            throw new NotFoundException("id" +id + "not present");
        }
        driverRepo.deleteById(id);
    }
}
