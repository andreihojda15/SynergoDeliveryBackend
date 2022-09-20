package com.synergo.deliverybe.controller;

import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.services.DriverService;
import org.junit.jupiter.api.Disabled;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DriverControllerTest {
    @MockBean
    private DriverService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void find_all_with_result() throws Exception {

        // given
        Car carr = new Car();
        carr.setId(1);

        Driver driver1 = new Driver();
        driver1.setCar(carr);
        Driver driver2 = new Driver();
        driver2.setCar(carr);
        Driver driver3 = new Driver();
        driver3.setCar(carr);

        List<Driver> allEmployees = Arrays.asList(driver1, driver2, driver3);

        given(service.getAll()).willReturn(allEmployees);

        // when
        mvc.perform(get("/api/drivers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        // then
        verify(service, times(1)).getAll();
        reset(service);
    }
   @Test
    public void deleteById_success() throws Exception{

        Driver driver1 = new Driver();
        driver1.setId(1);
        // when
        mvc.perform(delete("/api/drivers" + driver1.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        // then
        verify(service, times(1)).deleteById(driver1.getId());
        reset(service);
    }

}
