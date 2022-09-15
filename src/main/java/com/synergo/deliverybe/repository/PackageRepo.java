package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepo extends JpaRepository<Package, Integer> {

    List<Package> getPackagesByCustomer(Customer customer);
}
