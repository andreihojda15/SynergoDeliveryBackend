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
    private String id;
    private String name;
    private String phoneNumber;
    private String adresse;
    private String packageId;


    public static CustomerDto valueOf(Customer customer)
    {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .adresse(customer.getAdresse())
                .packageId(customer.getPackageId())
                .build();
    }
}
