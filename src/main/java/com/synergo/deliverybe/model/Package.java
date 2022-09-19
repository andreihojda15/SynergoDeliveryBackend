package com.synergo.deliverybe.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_phone")
    private String senderPhone;
    @Column(name = "departure_address")
    private String departureAddress;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column
    private String awb;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "recipient_phone")
    private String recipientPhone;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = true)
    private Car car;
}
