package com.synergo.deliverybe.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String address;
//
//    @OneToMany
//    private List<Package> packages;
}
