package com.synergo.deliverybe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Driver {

    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String phone;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
