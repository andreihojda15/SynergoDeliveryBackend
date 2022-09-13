package com.synergo.deliverybe.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Package {

    @Id
    private Integer id;
    @Column
    private String sender_name;
    @Column
    private String sender_phone;
    @Column
    private String departure_address;
    @Column
    private String departure_date;
    @Column
    private String awb;
    @Column
    private String delivery_address;
    @Column
    private String delivery_date;
    @Column
    private String recipient_name;
    @Column
    private String recipient_phone;
//    @Column
//    private Car car;

}
