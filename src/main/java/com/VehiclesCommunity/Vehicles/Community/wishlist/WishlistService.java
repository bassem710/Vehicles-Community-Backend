package com.VehiclesCommunity.Vehicles.Community.wishlist;

import com.VehiclesCommunity.Vehicles.Community.user.User;
import com.VehiclesCommunity.Vehicles.Community.vehicle.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Vehicle> getUserWishlist(Integer userId) {
        List<Wishlist> wishlists = wishlistRepository.findAllByUserId(userId);
        List<Vehicle> vehicles = new ArrayList<>();
        for (Wishlist wishlist : wishlists) {
            vehicles.add(wishlist.getVehicle());
        }
        return vehicles;
    }

    public void addToWishlist(Integer userId, Integer vehicleId) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(new User(userId));
        wishlist.setVehicle(new Vehicle(vehicleId));
        wishlistRepository.save(wishlist);
    }

    public void removeFromWishlist(Integer userId, Integer vehicleId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndVehicleId(userId, vehicleId);
        if (wishlist != null) {
            wishlistRepository.delete(wishlist);
        }
    }
}
