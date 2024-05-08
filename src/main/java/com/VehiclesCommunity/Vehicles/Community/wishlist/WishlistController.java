package com.VehiclesCommunity.Vehicles.Community.wishlist;

import com.VehiclesCommunity.Vehicles.Community.user.User;
import com.VehiclesCommunity.Vehicles.Community.vehicle.Vehicle;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping
    public ResponseEntity<Map<String, List<Vehicle>>> getUserWishlist(HttpServletRequest request) {
        User userDetails = (User) request.getAttribute("userDetails");
        return ResponseEntity.ok(Map.of("data", wishlistService.getUserWishlist(userDetails.getId())));
    }

    @PostMapping("/like/{vehicleId}")
    public ResponseEntity<Map<String, String>> addToWishlist(HttpServletRequest request, @PathVariable Integer vehicleId) {
        User userDetails = (User) request.getAttribute("userDetails");
        wishlistService.addToWishlist(userDetails.getId(), vehicleId);
        return ResponseEntity.ok(Map.of("message", "Vehicle added to wishlist successfully"));
    }

    @PostMapping("/unlike/{vehicleId}")
    public ResponseEntity<Map<String, String>> removeFromVehicle(HttpServletRequest request, @PathVariable Integer vehicleId) {
        User userDetails = (User) request.getAttribute("userDetails");
        wishlistService.removeFromWishlist(userDetails.getId(), vehicleId);
        return ResponseEntity.ok(Map.of("message", "Vehicle removed from wishlist successfully"));
    }
}
