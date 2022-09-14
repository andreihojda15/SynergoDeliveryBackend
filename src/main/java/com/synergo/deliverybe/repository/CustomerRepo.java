package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


//
//public class CustomerRepo {
//    List<Customer> customers = new ArrayList<>();
//    public List<Customer> getAll() {
//
//
//        if(customers.isEmpty()) {
//            customers.add(buildCustomer(1, "John Doe", "+1 331-254-1866", "Street Doverdale 201", "b39b0daa-82af-4617-bf30-49bd915fa46f"));
//            customers.add(buildCustomer(2, "Menelaus Rajko", "+1 201-872-9969", "2485 Worley Avenue", "f184efc5-7281-473e-baff-044600955b71"));
//            customers.add(buildCustomer(3,"Hanan Dorcas","+1 201-867-9401","Street Alonys 200","6ce585e6-9a7b-41ba-aa7a-57988914e82f"));
//            customers.add(buildCustomer(4,"Medousa Domitius","+1 582-201-1351","Street Peson 201","60de2190-26d6-4ac4-a8ed-359e028dc3e8"));
//            customers.add(buildCustomer(5,"Jacob Troilos","+1 505-950-4893","Street Aelsop 201","3731ba01-57b5-4742-a64d-0de061b382be"));
//            customers.add(buildCustomer(6,"Oisin Browning","+1 505-644-6540","1709 Heavner Avenue","3731ba01-57b5-4742-a64d-0de061b382b0"));
//            customers.add(buildCustomer(7,"Atif Kelley","+1 582-222-4932","910 Sherman Street","3731ba01-57b5-4742-a64d-0de061b382b1"));
//            customers.add(buildCustomer(8,"Camille Needham","+1 203-284-1579","3947 Stark Hollow Road","3731ba01-57b5-4742-a64d-0de061b382b2"));
//        }
//        return customers;
//    }
//
//    public Customer buildCustomer(Integer id,String name,String phone,String address, String packId)
//    {
//        Customer newCustomer = new Customer();
//
//        newCustomer.setId(id);
//        newCustomer.setName(name);
//        newCustomer.setPhoneNumber(phone);
//        newCustomer.setAddress(address);
//        newCustomer.setPackageId(packId);
//
//
//        return newCustomer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
