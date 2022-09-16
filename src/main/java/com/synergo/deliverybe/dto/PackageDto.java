package com.synergo.deliverybe.dto;

import com.synergo.deliverybe.model.Package;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {
    private Integer id;
    private String sender_name;
    private String sender_phone;
    private String departure_address;
    private Instant departure_date;
    private String awb;
    private String delivery_address;
    private Instant delivery_date;
    private String recipient_name;
    private String recipient_phone;
    private Integer customerId;

    public static PackageDto valueOf(Package pack){
        return PackageDto.builder()
                .id(pack.getId())
                .sender_name(pack.getSender_name())
                .sender_phone(pack.getSender_phone())
                .departure_address(pack.getDeparture_address())
                .departure_date(pack.getDeparture_date())
                .awb(pack.getAwb())
                .delivery_address(pack.getDelivery_address())
                .delivery_date(pack.getDelivery_date())
                .recipient_name(pack.getRecipient_name())
                .recipient_phone(pack.getRecipient_phone())
                .customerId(pack.getCustomer().getId())
                .build();
    }
}
