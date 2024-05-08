package com.VehiclesCommunity.Vehicles.Community.news;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column
    private String description;
    @Column
    private String title;
    @Column
    private String image;
    @Column
    private Date date;
}
