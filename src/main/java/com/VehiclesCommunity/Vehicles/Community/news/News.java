package com.VehiclesCommunity.Vehicles.Community.news;

import jakarta.persistence.*;
import lombok.*;

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
    private String desc;
    @Column
    private String title;
    @Column
    private String image;
}
