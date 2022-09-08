package com.synergo.deliverybe.model;

import lombok.Data;

@Data
public class Driver {

    private Integer id;
    private String name;
    private String phoneNo;
    private Car car;

}
