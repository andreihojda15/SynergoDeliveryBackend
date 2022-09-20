package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.repository.PackageRepo;
import com.synergo.deliverybe.services.PackageService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.support.NullValue;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PackageControllerTest {

    @MockBean
    private PackageService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void find_all_with_result() throws Exception {

        // given
        Customer alex = new Customer();
        alex.setId(1);

        Package package1 = new Package();
        package1.setCustomer(alex);
        Package package2 = new Package();
        package2.setCustomer(alex);
        Package package3 = new Package();
        package3.setCustomer(alex);

        List<Package> allEmployees = Arrays.asList(package1, package2, package3);

        given(service.getAll()).willReturn(allEmployees);

        // when
        mvc.perform(get("/api/packages").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        // then
        verify(service, times(1)).getAll();
        reset(service);
    }

    @Test
    public void find_by_id_exists() throws Exception {

        // given
        Customer alex = new Customer();
        alex.setId(1);

        Package package1 = new Package();
        package1.setCustomer(alex);

        int id = 2;
        package1.setId(id);

        given(service.getPackageById(id)).willReturn(Optional.of(package1));

        // when
        mvc.perform(get("/api/packages/" + id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", is(notNullValue())));

        // then
        verify(service, times(1)).getPackageById(id);
        reset(service);
    }

    @Test
    public void find_by_id_not_exists() throws Exception {

        // given
        int id = (int) (Math.random() * 100);
        given(service.getPackageById(id)).willReturn(Optional.empty());

        // when
        mvc.perform(get("/api/packages/" + id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

        // then
        verify(service, times(1)).getPackageById(id);
        reset(service);
    }

    @Test
    public void find_by_customer() throws Exception {

        // given
        Customer alex = new Customer();
        alex.setId(4);

        Package package1 = new Package();
        package1.setCustomer(alex);

        Package package2 = new Package();
        package2.setCustomer(alex);

        int id = (int) (Math.random() * 100);
        package1.setId(id);
        package2.setId(id);

        given(service.getAllPackagesByCustomer(alex.getId())).willReturn(List.of(package1, package2));

        // when
        mvc.perform(get("/api/packages/customer?customer_id=" + alex.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", is(notNullValue())));

        // then
        verify(service, times(1)).getAllPackagesByCustomer(alex.getId());
        reset(service);
    }

    @Test
    public void deleteById() throws Exception {
        // given
        Package package1 = new Package();
        int id = (int) (Math.random() * 100);
        package1.setId(id);

        // when
        mvc.perform(delete("/api/packages/" + package1.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // then
        verify(service, times(1)).deleteById(package1.getId());
        reset(service);
    }

    @Test
    public void deleteByIdFail() throws Exception {
        // given
        int id = (int) (Math.random() * 100);

        given(service.deleteById(id)).willThrow(new EntityNotFoundException());

        // when
        mvc.perform(delete("/api/packages/" + id).contentType(MediaType.APPLICATION_JSON));

        // then
        verify(service, times(1)).deleteById(id);
        reset(service);
    }
}
