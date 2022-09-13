package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Driver;

import java.util.Collections;
import java.util.List;

@Deprecated
public class DriverFileRepo implements IDriverRepo {

    @Override
    public List<Driver> getAll(int howMany) {
        return getDriversFromFile();
    }

    private List<Driver> getDriversFromFile() {
        //read content from file drivers_each_linec.csv and return

        return Collections.emptyList();
    }
}
