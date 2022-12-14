package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepo extends JpaRepository<Package, Integer> {

    List<Package> getPackagesByCustomer(Customer customer);

    @Query("select p from Package p where p.car = ?1")
    List<Package> findByCar(Car car);

    @Query("select p from Package p where p.customer.id = ?1")
    List<Package> findByCustomer_Id(Integer id);
}
