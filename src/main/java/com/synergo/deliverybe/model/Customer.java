package com.synergo.deliverybe.model;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String adresse;
    private String packageId;
}
