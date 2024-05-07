package com.VehiclesCommunity.Vehicles.Community.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findAllByUserId(Integer userId);

    Wishlist findByUserIdAndVehicleId(Integer userId, Integer vehicleId);
}
