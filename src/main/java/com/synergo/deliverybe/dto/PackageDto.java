package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {
    private Integer id;
    private String senderName;
    private String senderPhoneNo;
    private String departureAddress;
    private String departureDate;
    private String awb;
    private String deliveryAddress;
    private String deliveryDate;
    private String recipientName;
    private String recipientPhoneNo;
    private Integer carId;

    public static PackageDto valueOf(Package pack){
        return PackageDto.builder()
                .id(pack.getId())
                .senderName(pack.getSenderName())
                .senderPhoneNo(pack.getSenderPhoneNo())
                .departureAddress(pack.getDepartureAddress())
                .departureDate(pack.getDepartureDate())
                .awb(pack.getAwb())
                .deliveryAddress(pack.getDeliveryAddress())
                .deliveryDate(pack.getDeliveryDate())
                .recipientName(pack.getRecipientName())
                .recipientPhoneNo(pack.getRecipientPhoneNo())
                .carId(pack.getCar().getId())
                .build();
    }
}
