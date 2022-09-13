package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepo driverRepo;

    public List<Driver> getAll(int howMany) {
        return driverRepo.findAll();
    }
}
