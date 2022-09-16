package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

//public class DriverRepo {
//
//    private Random RANDOM = new Random(1_000_000);
//    List<Driver> drivers = new ArrayList<>();
//
//    public List<Driver> getAll() {
//        drivers.add(buildDriver("eb3aebe1-e3ee-4524-b87b-d62fd7f3ba71", "Brooks Maitland","+1 518-936-3292","d1c51f3b-f13f-407f-b6e2-46c5bbc747ad"));
//        drivers.add(buildDriver("813a7c01-ea18-4962-b152-71efcb5c2b05", "Jack Sparrow","+1 301-909-1968"));
//        drivers.add(buildDriver("5f93d951-1dc6-4632-86dd-7b6b3fc51191", "Nettie Prosper", "+1 812-649-8597","c1f0c515-4862-434c-91bb-b477504f5161"));
//        drivers.add(buildDriver("7f71a066-74b6-43c8-bda3-8a6fe4fc1ed9", "Mellony Lee", "+1 601-833-0545"));
//        drivers.add(buildDriver("1dfdfb6e-8791-4766-8fe4-9973b965e1ac", "Tarina Raegan", "+1 904-633-5106","49ce9298-161a-4a04-82c0-31640f05dc31"));
//        drivers.add(buildDriver("602ee2fa-e319-41aa-aac9-9ebad9d21c56", "Tanya Kamden", "+1 229-389-4702","be37bda1-0b19-43da-b874-ac359926737c"));
//        drivers.add(buildDriver("fc80d8f4-d2c3-460d-8596-fb26d41df3d8", "Cheryl Kaden", "+1 818-373-7905"));
//        drivers.add(buildDriver("0bc15d56-f542-4228-802b-6ed877b87474", "Jaydon Lacie", "+1 507-526-5727","bad6d196-a0f1-47bb-8e95-69ace085bcee"));
//        drivers.add(buildDriver("6a0ee78b-f782-47e7-b178-af373cfa159b", "Brennan Lexus", "+1 816-758-9938"));
//        drivers.add(buildDriver("329568b3-e130-4515-95ef-91cba734f52c", "Franklyn Brylee", "+1 972-798-9680"));
//        return drivers;
//    }
//
//    public Driver buildDriver(String id,String name, String... others) {
//
//        Driver driver = new Driver();
//
//        driver.setId(Integer.parseInt(id));
//        driver.setName(name);
//        driver.setPhoneNo(others[0]);
//        Car car = new Car();
//        car.setId(Integer.parseInt(others[1]));
//        car.setCostOfTheCar(RANDOM.nextDouble());
//        driver.setCar(car);
//
//        return driver;
//    }


public interface DriverRepo extends JpaRepository<Driver, Integer> {



//    public Driver buildDriver(String id, String name, String[] others) {
//        return buildDriver(id,name,others);
//    }
}
