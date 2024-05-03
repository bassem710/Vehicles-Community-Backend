package com.VehiclesCommunity.Vehicles.Community.vehicle;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name= "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Integer price;
    @Column
    private String image;
}
