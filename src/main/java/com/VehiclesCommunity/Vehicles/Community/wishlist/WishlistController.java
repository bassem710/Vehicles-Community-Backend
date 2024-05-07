package com.VehiclesCommunity.Vehicles.Community.wishlist;

import com.VehiclesCommunity.Vehicles.Community.vehicle.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, List<Vehicle>>> getVehicle(@PathVariable Integer userId) {
        return ResponseEntity.ok(Map.of("data", wishlistService.getUserWishlist(userId)));
    }

    @PostMapping("/like/{userId}/{vehicleId}")
    public ResponseEntity<Map<String, String>> addToWishlist(@PathVariable Integer userId, @PathVariable Integer vehicleId) {
        wishlistService.addToWishlist(userId, vehicleId);
        return ResponseEntity.ok(Map.of("message", "Vehicle added to wishlist successfully"));
    }

    @PostMapping("/unlike/{userId}/{vehicleId}")
    public ResponseEntity<Map<String, String>> removeFromVehicle(@PathVariable Integer userId, @PathVariable Integer vehicleId) {
        wishlistService.removeFromWishlist(userId, vehicleId);
        return ResponseEntity.ok(Map.of("message", "Vehicle removed from wishlist successfully"));
    }
}
