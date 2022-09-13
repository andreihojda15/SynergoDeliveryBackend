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
    private String phoneNo;
//    @OneToOne(fetch = FetchType.LAZY)
//    private Car car;

}
