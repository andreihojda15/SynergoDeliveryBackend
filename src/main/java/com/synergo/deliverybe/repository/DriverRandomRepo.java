package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Deprecated
public class DriverRandomRepo implements IDriverRepo {

    private Random RANDOM = new Random(1_000_000);

    public List<Driver> getAll(int howMany) {
        List<Driver> drivers = new ArrayList<>();

        for (int count = 0; count < howMany; count++) {
            drivers.add(buildRandomDriver());
        }
        drivers.add(buildDriver("1322", "312321"));
        drivers.add(buildDriver("1322", "gsdfsdfs", "312321", "321321"));
        drivers.add(buildDriver("1322", "gsdfsdfs", "312321"));

        return drivers;
    }

    private Driver buildRandomDriver() {
        Driver randomDriven = new Driver();

        int id = RANDOM.nextInt();
        randomDriven.setId(id);
        randomDriven.setName("name " + id);
        randomDriven.setPhone("phone " + id);

        Car car = new Car();
        car.setId(RANDOM.nextInt());
//        car.setCostOfTheCar(RANDOM.nextDouble());
//        randomDriven.setCar(car);

        return randomDriven;
    }

    private Driver buildDriver(String id, String name, String... others) {
        return null;

    }

}
