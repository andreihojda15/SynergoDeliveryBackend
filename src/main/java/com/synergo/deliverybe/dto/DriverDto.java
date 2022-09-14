package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private Integer id;
    private String name;
    private String phone;
    private Integer carId;


    public static DriverDto valueOf(Driver driver) {
        return DriverDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .phone(driver.getPhone())
//                .carId(driver.getCar().getId())
                .build();
    }

}
