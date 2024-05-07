package com.VehiclesCommunity.Vehicles.Community.wishlist;

import com.VehiclesCommunity.Vehicles.Community.user.User;
import com.VehiclesCommunity.Vehicles.Community.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "wishlist", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vehicle_id"})})
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
}
