package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.pack.id=?1")
    Car findByPackId(Integer id);
}

