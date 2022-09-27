package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CarDto {

    private Integer id;
    @NotBlank(message = "Registration number may not be blank")
    private String registrationNumber;
    private String status;

    public static CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .registrationNumber(car.getRegistrationNumber())
                .status(car.getStatus())
                .build();
    }

    public static Car fromDto(CarDto carDto) {
        return Car.builder()
                .registrationNumber(carDto.getRegistrationNumber())
                .status(carDto.getStatus())
                .build();
    }
}
