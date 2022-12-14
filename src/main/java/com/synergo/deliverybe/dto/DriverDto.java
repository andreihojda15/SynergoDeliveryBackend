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
    private String phoneNumber;
    private Integer carId;


    public static DriverDto toDto(Driver driver) {
        return DriverDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .phoneNumber(driver.getPhoneNumber())
                .carId(driver.getCar() != null ? driver.getCar().getId() : null)
                .build();
    }

    public static Driver fromDto(DriverDto carDto) {
        return Driver.builder()
                .name(carDto.getName())
                .phoneNumber(carDto.getPhoneNumber())
                .build();
    }

}
