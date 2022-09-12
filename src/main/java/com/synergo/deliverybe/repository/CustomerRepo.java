package com.synergo.deliverybe.repository;

import com.synergo.deliverybe.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepo {
    List<Customer> customers = new ArrayList<>();
    public List<Customer> getAll() {



        customers.add(buildCustomer("f39ed9fd-df1e-4486-9097-6128b366508b","John Doe","+1 331-254-1866","Street Doverdale 201","b39b0daa-82af-4617-bf30-49bd915fa46f"));
        customers.add(buildCustomer("f017a0d8-3604-4ff2-9610-184917694276","Menelaus Rajko","+1 201-872-9969","2485 Worley Avenue","f184efc5-7281-473e-baff-044600955b71"));
        customers.add(buildCustomer("80b069e5-4841-4b97-a7e0-c3c9d6260017","Hanan Dorcas","+1 201-867-9401","Street Alonys 200","6ce585e6-9a7b-41ba-aa7a-57988914e82f"));
        customers.add(buildCustomer("c214b525-b413-477e-b620-d78f95e32193","Medousa Domitius","+1 582-201-1351","Street Peson 201","60de2190-26d6-4ac4-a8ed-359e028dc3e8"));
        customers.add(buildCustomer("e59442e4-54cf-4ef8-8c75-9e49ccc3ed8f","Jacob Troilos","+1 505-950-4893","Street Aelsop 201","3731ba01-57b5-4742-a64d-0de061b382be"));
        customers.add(buildCustomer("e08c921f-556d-4dd5-bc2e-fa11f84c6cc3","Oisin Browning","+1 505-644-6540","1709 Heavner Avenue","3731ba01-57b5-4742-a64d-0de061b382b0"));
        customers.add(buildCustomer("df7356c0-4417-42fd-a231-00e8612eba12","Atif Kelley","+1 582-222-4932","910 Sherman Street","3731ba01-57b5-4742-a64d-0de061b382b1"));
        customers.add(buildCustomer("2a20f279-41c7-4235-90d8-a2daa9dda0b8","Camille Needham","+1 203-284-1579","3947 Stark Hollow Road","3731ba01-57b5-4742-a64d-0de061b382b2"));
        return customers;
    }

    public Customer buildCustomer(String id,String name,String ... others)
    {
        Customer newCustomer = new Customer();

        newCustomer.setId(id);
        newCustomer.setName(name);
        newCustomer.setPhoneNumber(others[0]);
        newCustomer.setAdresse(others[1]);
        newCustomer.setPackageId(others[2]);


        return newCustomer;
    }
}
