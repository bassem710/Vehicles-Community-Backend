package com.VehiclesCommunity.Vehicles.Community.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EventRepository extends JpaRepository<Event, Integer> {
}
