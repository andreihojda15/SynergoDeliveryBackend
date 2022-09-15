package com.synergo.deliverybe.services;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.repository.CarRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CarService {
    @Autowired
    private CarRepo carRepo;

    public List<Car> getAll() {
        return carRepo.findAll();

    }
}
