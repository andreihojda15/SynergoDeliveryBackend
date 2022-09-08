package com.synergo.deliverybe.model;

import lombok.Data;

@Data
public class Package {

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
    private Car car;


}
