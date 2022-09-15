package com.synergo.deliverybe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Car {

    @Id
    private Integer id;
//    @Column
//    private double costOfTheCar;
    @Column
    private String registration_number;
    @Column
    private String status;
    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Package pack;
    @OneToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

}
