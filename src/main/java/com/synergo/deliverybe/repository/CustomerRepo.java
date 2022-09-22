package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.name=?1")
    Customer findByName(String name);
}
