package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String address;

    public static CustomerDto valueOf(Customer customer)
    {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }

    public static Customer fromDto(CustomerDto customerDto)
    {
        return Customer.builder()
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
                .address(customerDto.getAddress())
                .build();
    }
}
