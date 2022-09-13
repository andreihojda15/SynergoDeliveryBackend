package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Driver;

import java.util.List;

@Deprecated
public interface IDriverRepo {

    List<Driver> getAll(int howMany);
}
