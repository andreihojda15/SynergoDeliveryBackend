package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer> {

    @Query("select d from Driver d where d.name = ?1")
    Optional<Driver> findByName(String name);

    @Query("select d from Driver d where d.car.id = ?1")
    Driver findByCar_Id(Integer id);
}
