package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {
    private Integer id;
    private String senderName;
    private String senderPhoneNumber;
    private String departureAddress;
    private LocalDate departureDate;
    private String awb;
    private String deliveryAddress;
    private LocalDate deliveryDate;
    private String recipientName;
    private String recipientPhone;
    private Integer customerId;
    private Integer carId;

    public static PackageDto valueOf(Package pack){
        return PackageDto.builder()
                .id(pack.getId())
                .senderName(pack.getSenderName())
                .senderPhoneNumber(pack.getSenderPhone())
                .departureAddress(pack.getDepartureAddress())
                .departureDate(pack.getDepartureDate())
                .awb(pack.getAwb())
                .deliveryAddress(pack.getDeliveryAddress())
                .deliveryDate(pack.getDeliveryDate())
                .recipientName(pack.getRecipientName())
                .recipientPhone(pack.getRecipientPhone())
                .customerId(pack.getCustomer().getId())
                .carId(pack.getCar() != null ? pack.getCar().getId() : null)
                .build();
    }
}
