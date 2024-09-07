package com.example.moviereservationsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "is_showing", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isShowing ;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ShowTime> showTimes;
}
