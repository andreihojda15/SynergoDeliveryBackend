package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

    @Query("SELECT d FROM Driver d WHERE d.name=?1")
    Driver findByName(String name);

    @Query("select d from Driver d where d.car.id = ?1")
    Driver findByCar_Id(Integer id);
}
