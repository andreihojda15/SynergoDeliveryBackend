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
    @Column
    private String registration_number;
    @Column
    private String status;

}
