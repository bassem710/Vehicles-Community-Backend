package com.VehiclesCommunity.Vehicles.Community.event;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name= "event")
public class Event {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String location;
    @Column
    private Date date;

}
