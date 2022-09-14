package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.repository.DriverRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DriverService {

    @Autowired
    private DriverRepo driverRepo;

    public List<Driver> getAll(int howMany) {
        log.debug("Someone called us with howMany: {}", howMany);
        return driverRepo.findAll();
    }
}
