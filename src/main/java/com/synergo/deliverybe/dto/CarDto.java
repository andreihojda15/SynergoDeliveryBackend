package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Driver;
import com.synergo.deliverybe.model.Car;
import com.synergo.deliverybe.model.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    private Integer id;
    private String registration_number;
    private String status;
    private Package pack;
    private Driver driver;
    public static CarDto valueOf(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .registration_number(car.getRegistration_number())
                .status(car.getStatus())
                .driver(car.getDriver())
                .pack(car.getPack())
                .build();
    }
}
