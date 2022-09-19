package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.model.Customer;
import com.synergo.deliverybe.model.Package;
import com.synergo.deliverybe.services.PackageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
}
